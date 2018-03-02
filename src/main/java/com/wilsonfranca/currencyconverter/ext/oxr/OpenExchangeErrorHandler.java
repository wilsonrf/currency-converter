package com.wilsonfranca.currencyconverter.ext.oxr;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wilsonfranca.currencyconverter.ext.oxr.exception.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * Created by wilson on 27/02/18.
 */
public class OpenExchangeErrorHandler implements ResponseErrorHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {

        HttpStatus.Series series = response.getStatusCode().series();

        return series.equals(HttpStatus.Series.SERVER_ERROR) || series.equals(HttpStatus.Series.CLIENT_ERROR);
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {

        HttpStatus httpStatus = response.getStatusCode();

        switch (httpStatus) {
            case BAD_REQUEST:
                handleBadRequest(response);
                break;
            case UNAUTHORIZED:
            case FORBIDDEN:
            case TOO_MANY_REQUESTS:
                handleUnauthorized(response);
                break;
            case NOT_FOUND:
                handleNotFound(response);
                break;
            default:
                handleDefaultClient(response);
        }

    }

    private void handleDefaultClient(ClientHttpResponse response) {
        logger.warn("Client error [{}]", response);
        throw new ClientException();
    }

    private void handleNotFound(ClientHttpResponse response) {
        logger.warn("Client error [{}]", response);
        throw new NotFoundException();
    }

    private void handleUnauthorized(ClientHttpResponse response) {
        logger.warn("Client error [{}]", response);
        throw new UnauthorizedException();
    }

    private void handleBadRequest(ClientHttpResponse response) {


        ObjectMapper objectMapper = new ObjectMapper();

        OpenExchangeError error = null;

        try {

            logger.warn("Client error [{}] [{}] [{}]", response.getStatusText(), response.getStatusCode(), response.getBody());

            error = objectMapper.readValue(response.getBody(), OpenExchangeError.class);

            if("invalid_date".equalsIgnoreCase(error.getMessage())) {
                throw new InvalidDateException();
            } else if ("not_available".equalsIgnoreCase(error.getMessage())) {
                throw new NotAvailableException();
            } else {
                throw new ClientException();
            }

        } catch (IOException e) {
            throw new ClientException();
        }



    }
}
