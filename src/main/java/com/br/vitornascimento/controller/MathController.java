package com.br.vitornascimento.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.vitornascimento.Operations;

@RestController
public class MathController {

	@RequestMapping("/sum/{numberOne}/{numberTwo}")
	public Double sum(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {

		return Operations.sum(numberOne, numberTwo);
	}

	@RequestMapping("/minus/{numberOne}/{numberTwo}")
	public Double minus(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {

		return Operations.subtraction(numberOne, numberTwo);

	}

	@RequestMapping("/multply/{numberOne}/{numberTwo}")
	public Double multply(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {

		return Operations.multiplication(numberOne, numberTwo);

	}

	@RequestMapping("/share/{numberOne}/{numberTwo}")
	public Double share(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {

		return Operations.division(numberOne, numberTwo);

	}

	@RequestMapping("/average/{numberOne}/{numberTwo}")
	public Double average(@PathVariable("numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo) {

		return Operations.mean(numberOne, numberTwo);

	}

	@RequestMapping("/root/{numberOne}")
	public Double root(@PathVariable("numberOne") String numberOne) {

		return Operations.squareRoot(numberOne);

	}

}
