package kr.or.ddit.common.exception;

import java.sql.SQLException;

/**
 * Persistence Layer(Dao)에서 SqlException 전환하는 용도로 사용할 예외
 *
 */
//다른 예외는 wrapping 할 수 없음 !
public class PersistenceException extends RuntimeException{

	public PersistenceException(String message, SQLException cause) { //SqlException 전환하는 용도
		super(message, cause); 
	}

	public PersistenceException(Throwable cause) {
		super(cause);
	}
	
}
