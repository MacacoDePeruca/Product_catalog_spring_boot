package com.br.vitornascimento;

import com.br.vitornascimento.exception.UnsuportedMathOperationException;

public class Operations {

	public static Double sum(String numberOne, String numberTwo) {

		if (!OperationValidator.isNumeric(numberOne) || !OperationValidator.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("please set a numeric value!");
		}

		double sum = OperationValidator.convertToDouble(numberOne) + OperationValidator.convertToDouble(numberTwo);

		return sum;

	}

	public static Double subtraction(String numberOne, String numberTwo) {

		if (!OperationValidator.isNumeric(numberOne) || !OperationValidator.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("please set a numeric value!");
		}

		return OperationValidator.convertToDouble(numberOne) - OperationValidator.convertToDouble(numberTwo);

	}

	public static Double division(String numberOne, String numberTwo) {

		if (!OperationValidator.isNumeric(numberOne) || !OperationValidator.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("please set a numeric value!");
		}

		return OperationValidator.convertToDouble(numberOne) / OperationValidator.convertToDouble(numberTwo);

	}

	public static Double multiplication(String numberOne, String numberTwo) {

		if (!OperationValidator.isNumeric(numberOne) || !OperationValidator.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("please set a numeric value!");
		}

		return OperationValidator.convertToDouble(numberOne) + OperationValidator.convertToDouble(numberTwo);

	}

	public static Double mean(String numberOne, String numberTwo) {

		if (!OperationValidator.isNumeric(numberOne) || !OperationValidator.isNumeric(numberTwo)) {
			throw new UnsuportedMathOperationException("please set a numeric value!");
		}


		return (OperationValidator.convertToDouble(numberOne) + OperationValidator.convertToDouble(numberTwo))/2;

	}

	public static Double squareRoot(String numberOne) {

		if (!OperationValidator.isNumeric(numberOne)) {
			throw new UnsuportedMathOperationException("please set a numeric value!");
		}

		return  Math.sqrt(OperationValidator.convertToDouble(numberOne));

	}
}
