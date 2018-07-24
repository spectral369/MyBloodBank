package com.bloodbank.statusHeader;

import java.math.BigInteger;

import org.vaadin.csvalidation.CSValidator;

import com.bloodbank.MyBloodBank.MyUI;
import com.bloodbank.database.BloodBankDatabase;
import com.bloodbank.utils.Utils;
import com.vaadin.data.Binder;
import com.vaadin.data.ValueProvider;
import com.vaadin.data.converter.StringToBigIntegerConverter;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.server.Setter;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.window.WindowMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.PasswordField;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class SignUp extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Binder<SignUp> binder = null;
	private VerticalLayout main = null;
	private HorizontalLayout titlelayout = null;
	private Button title = null;
	private HorizontalLayout nameLayout = null;
	private Button nameTitle = null;
	private HorizontalLayout nameField = null;
	private TextField firstName = null;
	private TextField lastName = null;
	private HorizontalLayout mailLayout = null;
	private Button mailTitle = null;
	private HorizontalLayout cnpLayout = null;
	private TextField cnpField = null;
	private HorizontalLayout passTitle = null;
	private Button passwordTitle = null;
	private HorizontalLayout passField = null;
	private PasswordField passwordField = null;
	private HorizontalLayout passConfirmTitle = null;
	private Button passwordConfirmTitle = null;
	private HorizontalLayout passConfirmField = null;
	private HorizontalLayout termsLayout = null;
	private PasswordField passwordConfirmField = null;
	private CheckBox terms = null;
	private HorizontalLayout btnLayout = null;
	private Button signup = null;
	private Button cancel = null;

	public SignUp(MyUI ui, BloodBankDatabase db) {

		binder = new Binder<>(SignUp.class);

		main = new VerticalLayout();

		titlelayout = new HorizontalLayout();
		title = new Button("Sign Up Form", VaadinIcons.SIGN_IN_ALT);
		title.setEnabled(false);
		title.addStyleNames(ValoTheme.BUTTON_BORDERLESS, "clearDisabled");
		titlelayout.addComponent(title);
		titlelayout.setComponentAlignment(title, Alignment.TOP_CENTER);
		titlelayout.addStyleName(ValoTheme.MENU_ROOT);
		titlelayout.setWidth("100%");
		main.addComponent(titlelayout);
		main.setComponentAlignment(titlelayout, Alignment.TOP_CENTER);

		nameLayout = new HorizontalLayout();
		nameTitle = new Button("Full name", VaadinIcons.INFO_CIRCLE);
		nameTitle.setEnabled(false);
		nameTitle.addStyleNames(ValoTheme.BUTTON_BORDERLESS, "clearDisabled");
		nameLayout.addComponent(nameTitle);
		nameLayout.setComponentAlignment(nameTitle, Alignment.TOP_CENTER);
		main.addComponent(nameLayout);
		main.setComponentAlignment(nameLayout, Alignment.TOP_CENTER);

		nameField = new HorizontalLayout();
		firstName = new TextField();
		firstName.setRequiredIndicatorVisible(true);
		firstName.setPlaceholder("First Name");

		lastName = new TextField();
		lastName.setRequiredIndicatorVisible(true);
		lastName.setPlaceholder("Last Name");
		nameField.addComponents(firstName, lastName);
		nameField.setComponentAlignment(firstName, Alignment.MIDDLE_CENTER);
		nameField.setComponentAlignment(lastName, Alignment.MIDDLE_CENTER);
		main.addComponent(nameField);
		main.setComponentAlignment(nameField, Alignment.MIDDLE_CENTER);

		mailLayout = new HorizontalLayout();
		mailTitle = new Button("CNP:", VaadinIcons.USER_HEART);
		mailTitle.setEnabled(false);
		mailTitle.addStyleNames(ValoTheme.BUTTON_BORDERLESS, "clearDisabled");
		mailLayout.addComponent(mailTitle);
		mailLayout.setComponentAlignment(mailTitle, Alignment.TOP_CENTER);
		main.addComponent(mailLayout);
		main.setComponentAlignment(mailLayout, Alignment.TOP_CENTER);

		cnpLayout = new HorizontalLayout();
		cnpField = new TextField();
		cnpField.setRequiredIndicatorVisible(true);
		cnpField.setPlaceholder("CNP:");
		cnpLayout.addComponent(cnpField);
		cnpLayout.setComponentAlignment(cnpField, Alignment.MIDDLE_CENTER);
		main.addComponent(cnpLayout);
		main.setComponentAlignment(cnpLayout, Alignment.MIDDLE_CENTER);

		passTitle = new HorizontalLayout();
		passwordTitle = new Button("Passoword", VaadinIcons.LOCK);
		passwordTitle.setEnabled(false);
		passwordTitle.addStyleNames(ValoTheme.BUTTON_BORDERLESS, "clearDisabled");
		passTitle.addComponent(passwordTitle);
		passTitle.setComponentAlignment(passwordTitle, Alignment.TOP_CENTER);
		main.addComponent(passTitle);
		main.setComponentAlignment(passTitle, Alignment.TOP_CENTER);

		passField = new HorizontalLayout();
		passwordField = new PasswordField();
		passwordField.setPlaceholder("******");
		passwordField.setRequiredIndicatorVisible(true);
		passField.addComponent(passwordField);
		passField.setComponentAlignment(passwordField, Alignment.MIDDLE_CENTER);
		main.addComponent(passField);
		main.setComponentAlignment(passField, Alignment.MIDDLE_CENTER);
		//////
		passConfirmTitle = new HorizontalLayout();
		passwordConfirmTitle = new Button("Confirm Passoword", VaadinIcons.LOCK);
		passwordConfirmTitle.setEnabled(false);
		passwordConfirmTitle.addStyleNames(ValoTheme.BUTTON_BORDERLESS, "clearDisabled");
		passConfirmTitle.addComponent(passwordConfirmTitle);
		passConfirmTitle.setComponentAlignment(passwordConfirmTitle, Alignment.TOP_CENTER);
		main.addComponent(passConfirmTitle);
		main.setComponentAlignment(passConfirmTitle, Alignment.TOP_CENTER);

		passConfirmField = new HorizontalLayout();
		passwordConfirmField = new PasswordField();
		passwordConfirmField.setPlaceholder("******");
		passwordConfirmField.setRequiredIndicatorVisible(true);
		passConfirmField.addComponent(passwordConfirmField);
		passConfirmField.setComponentAlignment(passwordConfirmField, Alignment.MIDDLE_CENTER);
		main.addComponent(passConfirmField);
		main.setComponentAlignment(passConfirmField, Alignment.MIDDLE_CENTER);

		////

		termsLayout = new HorizontalLayout();
		terms = new CheckBox("Terms && conditions", false);
		terms.setRequiredIndicatorVisible(true);
		terms.setResponsive(true);

		termsLayout.addComponent(terms);
		termsLayout.setComponentAlignment(terms, Alignment.TOP_CENTER);
		main.addComponent(termsLayout);
		main.setComponentAlignment(termsLayout, Alignment.TOP_CENTER);

		btnLayout = new HorizontalLayout();
		signup = new Button("Sign up", VaadinIcons.SIGN_IN_ALT);
		signup.setEnabled(false);
		signup.addStyleName(ValoTheme.BUTTON_PRIMARY);
		signup.addClickListener(event -> {

			int r = db.registerSimpleUser(firstName.getValue().toLowerCase().trim(),
					lastName.getValue().toLowerCase().trim(), cnpField.getValue().trim(), passwordField.getValue());
			this.close();
			Notification notif = new Notification("Registration Success !", Notification.Type.HUMANIZED_MESSAGE);
			notif.setPosition(Position.BOTTOM_RIGHT);
			notif.setDelayMsec(4000);
			if (r > 0) {
				notif.show(Page.getCurrent());
			} else {
				notif.setCaption("Registration failed !");

				notif.show(Page.getCurrent());
			}

		});
		signup.setDisableOnClick(true);

		// test
		CSValidator csvfn = new CSValidator();
		String regex = "^$|[a-zA-Z]+";
		csvfn.setRegExp(regex);
		csvfn.setPreventInvalidTyping(true);
		csvfn.extend(firstName);
		CSValidator csvln = new CSValidator();
		csvln.setRegExp(regex);
		csvln.setPreventInvalidTyping(true);
		csvln.extend(lastName);

		binder.forField(firstName)
				.withValidator(name -> name.length() > 2, "First Name must have at least 3 characters !")
				.bind(new ValueProvider<SignUp, String>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public String apply(SignUp source) {
						return null;
					}
				}, new Setter<SignUp, String>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void accept(SignUp bean, String fieldvalue) {

					}
				});
		binder.forField(lastName)
				.withValidator(name -> name.length() > 2, "Last Name must have at least 3 characters !")
				.bind(new ValueProvider<SignUp, String>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public String apply(SignUp source) {
						return null;
					}
				}, new Setter<SignUp, String>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void accept(SignUp bean, String fieldvalue) {

					}
				});
		
		binder.forField(cnpField)
		//.withValidator(new EmailValidator("Invalid E-mail !"))
		.withConverter(new StringToBigIntegerConverter("CNP must be Integer"))
		.withValidator(str->Utils.getDigitCount(str)==13, "Invalid CNP Length!")
		
				.withValidator(str -> db.checkCNP(str)==0,
						"CNP already in use ! ")
				.bind(new ValueProvider<SignUp, BigInteger>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public BigInteger apply(SignUp source) {
						return null;
					}
				}, new Setter<SignUp, BigInteger>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void accept(SignUp bean, BigInteger fieldvalue) {

					}
				});
		binder.forField(passwordField)
				.withValidator(str -> str.length() > 5, "Password Must be at least 6 characters !")
				.bind(new ValueProvider<SignUp, String>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public String apply(SignUp source) {
						return null;
					}
				}, new Setter<SignUp, String>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void accept(SignUp bean, String fieldvalue) {

					}
				});
		binder.forField(passwordConfirmField)
				.withValidator(str -> str.length() > 5, "Password Must be at least 6 characters !")
				.withValidator(pass -> passwordField.getValue().equals(passwordConfirmField.getValue()),
						"Password don't match !")
				.bind(new ValueProvider<SignUp, String>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public String apply(SignUp source) {
						return null;
					}
				}, new Setter<SignUp, String>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void accept(SignUp bean, String fieldvalue) {

					}
				});
		binder.forField(terms).withValidator(val -> val == true, "You must agree with terms & conditions !")
				.bind(new ValueProvider<SignUp, Boolean>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public Boolean apply(SignUp source) {
						return null;
					}
				}, new Setter<SignUp, Boolean>() {

					/**
					 * 
					 */
					private static final long serialVersionUID = 1L;

					@Override
					public void accept(SignUp bean, Boolean fieldvalue) {

					}
				});

		binder.addStatusChangeListener(event -> {
			if (binder.isValid())
				signup.setEnabled(true);
			else
				signup.setEnabled(false);
		});

		cancel = new Button("Cancel", VaadinIcons.CLOSE);
		cancel.addClickListener(event -> {
			this.close();
		});
		btnLayout.addComponents(signup, cancel);
		btnLayout.setComponentAlignment(signup, Alignment.BOTTOM_RIGHT);
		btnLayout.setComponentAlignment(cancel, Alignment.BOTTOM_RIGHT);
		main.addComponent(btnLayout);
		main.setComponentAlignment(btnLayout, Alignment.BOTTOM_RIGHT);

		setContent(main);
		setWindowMode(WindowMode.NORMAL);
		setResizable(false);

		setClosable(false);
		setWidth(Page.getCurrent().getBrowserWindowWidth() / 2, Unit.PIXELS);
		setHeight(Page.getCurrent().getBrowserWindowHeight() / 1.5f, Unit.PIXELS);
	}
	

}
