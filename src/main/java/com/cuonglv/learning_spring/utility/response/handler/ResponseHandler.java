package com.cuonglv.learning_spring.utility.response.handler;

import java.util.Date;
import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.cuonglv.learning_spring.utility.exception.ErrorDetail;
import com.cuonglv.learning_spring.utility.exception.ServiceException;
import com.cuonglv.learning_spring.utility.model.msg.response.Body;
import com.cuonglv.learning_spring.utility.model.msg.response.Header;
import com.cuonglv.learning_spring.utility.model.msg.response.ResponseMessage;

@Component
public class ResponseHandler {

	private String source = "LearningSpring";

	private String target = "LearningSpring";

	private static final int SUCCESS_CODE = 0;
	private static final int FAIL_CODE = 1;
	private static final String SUCCESS_MESSAGE = "Thực thi thành công";
	private static final String FAIL_MESSAGE = "Thực thi không thành công";

	public ResponseMessage<?> generateResponseMessage(Object data, String requestId) {

		ResponseMessage<?> responseMessage = null;

		ErrorDetail errorDetail = null;
		Header header = new Header(source, target, requestId, new Date(), SUCCESS_CODE, SUCCESS_MESSAGE, errorDetail);
		Body<?> body = new Body<>(data);

		responseMessage = new ResponseMessage<>(header, body);

		return responseMessage;
	}

	public ResponseMessage<?> generateResponseMessage(Exception ex, String requestId) {

		ResponseMessage<?> responseMessage = null;

		ErrorDetail errorDetail = new ErrorDetail(Objects.isNull(HttpStatus.INTERNAL_SERVER_ERROR.value()) ? -99
				: HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getLocalizedMessage(), ex.getStackTrace().toString());
		Header header = new Header(source, target, requestId, new Date(), FAIL_CODE, FAIL_MESSAGE, errorDetail);
		Body<?> body = new Body<>(null);

		responseMessage = new ResponseMessage<>(header, body);

		ex.printStackTrace();
		return responseMessage;
	}

	public ResponseMessage<?> generateResponseMessage(ServiceException ex, String requestId) {

		ResponseMessage<?> responseMessage = null;

		ErrorDetail errorDetail = new ErrorDetail(
				Objects.isNull(ex.getHttpStatus().value()) ? -99 : ex.getHttpStatus().value(), ex.getLocalizedMessage(),
				null);
		Header header = new Header(source, target, requestId, new Date(), FAIL_CODE, FAIL_MESSAGE, errorDetail);
		Body<?> body = new Body<>(null);

		responseMessage = new ResponseMessage<>(header, body);

		return responseMessage;
	}
}
