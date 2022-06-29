package com.alura.hotel;

import java.awt.EventQueue;

import com.alura.hotel.config.Config;
import com.alura.hotel.views.MenuPrincipal;

public class HotelAppMain {
	public static void main(String[] args) {
		Config.init();

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				MenuPrincipal frame = new MenuPrincipal();
				frame.setVisible(true);
			}
		});
		
	}
}
