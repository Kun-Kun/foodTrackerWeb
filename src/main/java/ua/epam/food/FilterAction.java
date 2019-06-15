/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.food;

import java.io.IOException;
import javax.servlet.ServletException;

/**
 *
 * @author nmcdo5
 */
public interface FilterAction {
	void execute() throws IOException, ServletException;
}
