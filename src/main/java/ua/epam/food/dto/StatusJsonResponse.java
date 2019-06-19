/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.food.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author nmcdo5
 */
@Data
@AllArgsConstructor
public class StatusJsonResponse {
	private String status;
	private String message;
}
