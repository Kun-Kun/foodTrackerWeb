/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ua.epam.food.core.security.filter.builder;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *
 * @author nmcdo5
 */
public interface FilterAction {
	void execute(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException;
}
