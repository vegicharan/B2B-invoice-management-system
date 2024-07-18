package com.highradius;

import java.io.BufferedReader;
import java.io.IOException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class DeleteServlet
 */

@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

//	System.out.println(response.getWriter().append("Served at: ").append(request.getContextPath()));

		try {
			BufferedReader reader = request.getReader();
			HashMap<String, ArrayList<Integer>> map;
			Gson gson = new Gson();
			map = gson.fromJson(reader, HashMap.class);

			ArrayList<Integer> ar = map.get("sl_no");
			System.out.println(ar);

			Connection con = DBconnection.createConnect();
			String q = "UPDATE winter_internship SET is_deleted=1 WHERE sl_no=?";
			for (int i = 0; i < ar.size(); i++) {
				Statement st = con.createStatement();

				st.executeUpdate("UPDATE winter_internship SET is_deleted=1 WHERE sl_no=" + ar.get(i));

			}
			response.getWriter().append("Served at: ").append(request.getContextPath());
			con.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
