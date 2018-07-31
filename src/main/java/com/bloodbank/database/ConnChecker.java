package com.bloodbank.database;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import com.bloodbank.MyBloodBank.MyUI;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.server.Page;
import com.vaadin.ui.UI;

@PreserveOnRefresh
public class ConnChecker extends Thread {

	private UI ui;
	private MyUI myui;
	private BloodBankDatabase db;

	private static ConnChecker myConn = null;

	// private BloodBankHeader header;

	public static ConnChecker getInstance(MyUI ui, BloodBankDatabase db) {
		if (myConn == null) {
			myConn = new ConnChecker(ui, db);

		}
		return myConn;
	}

	public static ConnChecker getInstance() {
		return myConn;
	}

	private /* static */boolean wasFailed = false;

	public ConnChecker(MyUI ui, BloodBankDatabase db/* , BloodBankHeader header */) {
		this.ui = UI.getCurrent();
		this.db = db;
		// this.header=header;
		this.setDaemon(true);/// check daemon 'cuz destroy context in tomcat is called after restart

	}

	public void setUI(MyUI ui) {
		this.ui = ui;
	}

	@Override
	public void run() {
		try {
		/*	UI.setCurrent(ui);
			if(ui == null)
				throw new IllegalStateException("erroooor");*/
			ui.getUI().access(new Runnable() {

				@Override
				public void run() {
					// try {

					System.out.println(
							"again: " + new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH).format(new Date())
									+ " " + Thread.currentThread().getId());

				//	if (!db.isvalid()) {
					if (db.isvalid()<1) {
						int code = db.dbInit();
						System.out.println("Code dbcheck: " + code);
						wasFailed = true;
						if (code > 0) {

							try {

								db.close();
							} catch (Exception e) {
								e.printStackTrace();
							}
							System.out.println("reload here !!!!");
							Page.getCurrent().reload();
						} else {
							myui.header.circleStatus.removeStyleName("green");
							myui.header.circleStatus.addStyleName("red");
							myui.header.circleStatus.setDescription("No db connection!");
							myui.getNavigator().navigateTo("/Home");
							if (myui.getCP() != null)//
								myui.getMenuContainer().setEnabled(false);
						}
					} else if (wasFailed) {
						int code = db.dbInit();
						if (code > 0) {
							try {
								db.close();
							} catch (Exception e) {
								e.printStackTrace();
							}
							wasFailed = false;
							System.out.println("reload here !!!!");
							Page.getCurrent().reload();
						}
					}
					/*
					 * if(db.isvalid()getConnection().getConnection().isValid(2000)) { //
					 * db.getConnection().getConnection().close();
					 * header.circleStatus.removeStyleName("red");
					 * header.circleStatus.addStyleName("green");
					 * header.circleStatus.setDescription("DB connection OK!"); if(wasFailed) {
					 * wasFailed = false; Page.getCurrent().reload(); } } else { wasFailed=true; int
					 * code = db.dbInit(); if(code>0) { Page.getCurrent().reload(); wasFailed=false;
					 * } }
					 */
					/*
					 * }catch(Exception e) {
					 * 
					 * header.circleStatus.removeStyleName("green");
					 * header.circleStatus.addStyleName("red");
					 * header.circleStatus.setCaption("No db connection!"); wasFailed=true;
					 * 
					 * }
					 */
					try {
						join();
						// System.out.println("joined!!!!!!");
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("reload errror " + e.getMessage());

		}
	}

}
