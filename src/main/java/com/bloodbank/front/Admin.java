package com.bloodbank.front;

import java.time.LocalDateTime;

import com.bloodbank.database.BloodBankDatabase;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.shared.ui.datefield.DateTimeResolution;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Composite;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.DateTimeField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class Admin extends Composite implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private VerticalLayout root = null;
	private HorizontalLayout titleLayout = null;
	private Button title = null;

	private HorizontalLayout searchLayout;

	private TextField searchField;

	private Button searchBtn;

	private HorizontalLayout nameGenderAndCountryLayout;

	private TextField firstNameField;

	private TextField lastNameField;

	private TextField genderField;

	private TextField countryField;

	private TextField emailField;

	private HorizontalLayout ageCnpEligibleSignUpDateLayout;

	private TextField ageField;

	private TextField cnpField;

	private TextField eligibleField;

	private HorizontalLayout nationalityBloodTypeDeseasesLastDonationDateLayout;

	private TextField nationalityField;

	private TextField bloodTypeField;

	private TextArea deseasesField;

	private DateTimeField lastDonationDateField;

	private HorizontalLayout btnLayout;

	private Button btnpreviewFile;

	private Button btnUpdateDonorInfo;

	private Button btnExportToPDF;

	private Button btnClear;

	private Button btnNewDonation;

	private TextField signupDate;

	/*
	 * private HorizontalLayout infoTitlelayout;
	 * 
	 * private Button infoTitle;
	 */

	public Admin(BloodBankDatabase db) {
		root = new VerticalLayout();

		 titleLayout = new HorizontalLayout();
		title = new Button("Administrator panel", VaadinIcons.EXCLAMATION_CIRCLE);
		title.setEnabled(false);
		title.addStyleNames(ValoTheme.BUTTON_BORDERLESS, ValoTheme.MENU_ROOT, "clearDisabled");
		titleLayout.addComponent(title);
		titleLayout.setComponentAlignment(title, Alignment.TOP_CENTER);
		root.addComponent(titleLayout);
		root.setComponentAlignment(titleLayout, Alignment.TOP_CENTER);
		
		searchLayout =  new HorizontalLayout();
		searchLayout.setWidth("100%");
		CssLayout holderLayout = new CssLayout();
		searchField =  new TextField();
		searchField.setPlaceholder("Search for donor using CNP");
		searchField.setWidth(Page.getCurrent().getBrowserWindowWidth()/3,Unit.PIXELS);
		searchBtn =  new Button("Search",VaadinIcons.SEARCH);
		searchBtn.addClickListener(event ->{
			System.out.println("search clicked!");
			//search using cnp
		});
		
		holderLayout.addComponents(searchField,searchBtn);
		holderLayout.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		searchLayout.addComponent(holderLayout);
		searchLayout.setComponentAlignment(holderLayout, Alignment.MIDDLE_CENTER);
		root.addComponent(searchLayout);
		root.setComponentAlignment(searchLayout, Alignment.MIDDLE_CENTER);
		
		/*infoTitlelayout =  new HorizontalLayout();
		infoTitle =  new Button("Donor record information",VaadinIcons.EXCLAMATION_CIRCLE_O);
		infoTitle.setEnabled(false);
		infoTitle.addStyleNames(ValoTheme.BUTTON_BORDERLESS, ValoTheme.MENU_ROOT, "clearDisabled");
		infoTitlelayout.addComponent(infoTitle);
		infoTitlelayout.setComponentAlignment(infoTitle, Alignment.MIDDLE_CENTER);
		root.addComponent(infoTitlelayout);
		root.setComponentAlignment(infoTitlelayout, Alignment.MIDDLE_CENTER);*/
		
		nameGenderAndCountryLayout =  new HorizontalLayout();
		nameGenderAndCountryLayout.setWidth("100%");
		firstNameField =  new TextField("First Name:");
		firstNameField.setPlaceholder("Given Name");
		lastNameField =  new TextField("Last Name:");
		lastNameField.setPlaceholder("Family name");
		genderField =  new TextField("Gender:");
		genderField.setPlaceholder("Gender");
		countryField =  new TextField("Country/County:");
		countryField.setPlaceholder("Country/County");
		emailField = new TextField("E-Mail:");
		emailField.setEnabled(false);
		
		nameGenderAndCountryLayout.addComponents(firstNameField,lastNameField,genderField,countryField,emailField);
		nameGenderAndCountryLayout.setComponentAlignment(firstNameField, Alignment.MIDDLE_CENTER);
		nameGenderAndCountryLayout.setComponentAlignment(lastNameField, Alignment.MIDDLE_CENTER);
		nameGenderAndCountryLayout.setComponentAlignment(genderField, Alignment.MIDDLE_CENTER);
		nameGenderAndCountryLayout.setComponentAlignment(countryField, Alignment.MIDDLE_CENTER);
		nameGenderAndCountryLayout.setComponentAlignment(emailField, Alignment.MIDDLE_CENTER);
		
		root.addComponent(nameGenderAndCountryLayout);
		root.setComponentAlignment(nameGenderAndCountryLayout, Alignment.MIDDLE_CENTER);
		
		
		ageCnpEligibleSignUpDateLayout =  new HorizontalLayout();
		ageField =  new TextField("Birthdate:");
		ageField.setPlaceholder("Birthdate");
		cnpField =  new TextField("CNP");
		cnpField.setPlaceholder("CNP");
		eligibleField =  new TextField("Eligible:");
		eligibleField.setPlaceholder("Eligible");
		signupDate =  new TextField("Signup Date");
		signupDate.setPlaceholder("Signup date");
		signupDate.setEnabled(false);
		ageCnpEligibleSignUpDateLayout.addComponents(ageField,cnpField,eligibleField,signupDate);
		ageCnpEligibleSignUpDateLayout.setComponentAlignment(ageField, Alignment.MIDDLE_CENTER);
		ageCnpEligibleSignUpDateLayout.setComponentAlignment(cnpField, Alignment.MIDDLE_CENTER);
		ageCnpEligibleSignUpDateLayout.setComponentAlignment(eligibleField, Alignment.MIDDLE_CENTER);
		ageCnpEligibleSignUpDateLayout.setComponentAlignment(signupDate, Alignment.MIDDLE_CENTER);
		
		root.addComponent(ageCnpEligibleSignUpDateLayout);
		root.setComponentAlignment(ageCnpEligibleSignUpDateLayout, Alignment.MIDDLE_CENTER);
		
		
		nationalityBloodTypeDeseasesLastDonationDateLayout =  new HorizontalLayout();
		nationalityField =  new TextField("Nationality");
		nationalityField.setPlaceholder("Nationality");
		bloodTypeField =  new TextField("BloodType");
		bloodTypeField.setPlaceholder("BloodType");
		bloodTypeField.addStyleName(ValoTheme.TEXTFIELD_ALIGN_CENTER);
		deseasesField =  new TextArea("Deseases");
		deseasesField.setPlaceholder("Deseases, separeted by comma(,)");
		deseasesField.setRows(3);
		lastDonationDateField  = new DateTimeField("Last Donation Date");
		lastDonationDateField.setDateFormat("yyyy-MM-dd HH:mm:ss");
		lastDonationDateField.setResolution(DateTimeResolution.SECOND);
		lastDonationDateField.setValue(LocalDateTime.now());
		lastDonationDateField.setRequiredIndicatorVisible(true);
		
		nationalityBloodTypeDeseasesLastDonationDateLayout.addComponents(nationalityField,bloodTypeField,deseasesField,lastDonationDateField);
		nationalityBloodTypeDeseasesLastDonationDateLayout.setComponentAlignment(nationalityField, Alignment.MIDDLE_CENTER);
		nationalityBloodTypeDeseasesLastDonationDateLayout.setComponentAlignment(bloodTypeField, Alignment.MIDDLE_CENTER);
		nationalityBloodTypeDeseasesLastDonationDateLayout.setComponentAlignment(deseasesField, Alignment.MIDDLE_CENTER);
		nationalityBloodTypeDeseasesLastDonationDateLayout.setComponentAlignment(lastDonationDateField, Alignment.MIDDLE_CENTER);
		root.addComponent(nationalityBloodTypeDeseasesLastDonationDateLayout);
		root.setComponentAlignment(nationalityBloodTypeDeseasesLastDonationDateLayout, Alignment.MIDDLE_CENTER);
		
		
		
		
		btnLayout =  new HorizontalLayout();
		btnpreviewFile =  new Button("Preview File",VaadinIcons.FILE_PRESENTATION);
		btnUpdateDonorInfo =  new Button("Update donor",VaadinIcons.UPLOAD);
		btnNewDonation =  new Button("New Donation", new ThemeResource("bloodDrop.png"));
		btnNewDonation.addStyleNames(ValoTheme.BUTTON_PRIMARY,"bloodSum");
		btnExportToPDF =  new Button("Export to PDF",VaadinIcons.DOWNLOAD);
		btnClear =  new Button("Clear Fields",VaadinIcons.DEL_A);
		btnLayout.addComponents(btnpreviewFile,btnUpdateDonorInfo,btnNewDonation,btnExportToPDF,btnClear);
		root.addComponent(btnLayout);
		root.setComponentAlignment(btnLayout, Alignment.MIDDLE_CENTER);
		
		
		
		
		
		
		
			
		
		
		
		
		
		

		root.setMargin(false);
		root.addStyleName(ValoTheme.PANEL_WELL);
		setCompositionRoot(root);

	}

}
