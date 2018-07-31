package com.bloodbank.front;

import java.util.List;
import java.util.Map;

import com.bloodbank.database.BloodBankDatabase;
import com.bloodbank.utils.AttNames;
import com.bloodbank.windows.UpdateInfo;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.Page;
import com.vaadin.server.ThemeResource;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Composite;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class MyInfo extends Composite implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VerticalLayout page = null;
	private HorizontalLayout title = null;
	private Label def = null;
	private HorizontalLayout stsLayout = null;
	private Button sts = null;
	private HorizontalLayout nameTitle = null;
	private Button nameTitleLabel = null;
	private HorizontalLayout fullNamelayout = null;
	private CssLayout firstNameLayout = null;
	private Button firstNameLabel = null;
	private Label firstName = null;
	private CssLayout lastNameLayout = null;
	private Button lastNameLabel = null;
	private Label lastName = null;
	private CssLayout sexLayout = null;
	private Button sexLabel = null;
	private Label sex = null;
	private CssLayout countryLayout = null;
	private Button countryLabel = null;
	private Label country = null;
	private HorizontalLayout bloodInfoTitle = null;
	private Button bloodInfoLabel = null;
	private HorizontalLayout eligibleLayout = null;
	private CssLayout eligibleContainer = null;
	private Button eligible = null;
	private Label eligibleVal = null;
	private HorizontalLayout bloodInfo = null;
	private CssLayout bloodTypeLayout = null;
	private Button bloodTypeLabel = null;
	private Label bloodType = null;
	private CssLayout bloodQuantityDonated = null;
	private Button quantityTitle = null;
	private Label quantity = null;
	private CssLayout lastDonationDateLayout = null;
	private Button lastDonationDateTitle = null;
	private Label lastDonationDate = null;
	private CssLayout nextDonationDateLayout = null;
	private Button nextDonationDateTitle = null;
	private Label nextDonationDate = null;
	private HorizontalLayout medicalUnitTitle = null;
	private Label medicalUnitLabel = null;
	private Button updateInfo;
	private Panel content1;

	public MyInfo(BloodBankDatabase db) {
		
		page = new VerticalLayout();
		title = new HorizontalLayout();
		def = new Label("My Information");
		def.setEnabled(false);
		content1  =new Panel();
		content1.setSizeFull();
		
		title.addComponent(def);
		title.setComponentAlignment(def, Alignment.TOP_CENTER);
		page.addComponent(title);
		page.setComponentAlignment(title, Alignment.TOP_CENTER);
		/// new 20
	//	if(db.isUpdatedUser(VaadinSession.getCurrent().getAttribute(AttNames.USERID.getAtt()).toString())>0) {
		stsLayout = new HorizontalLayout();
		sts = new Button("Will be updated upon vising the donation center!", VaadinIcons.INFO_CIRCLE_O);
		sts.setEnabled(false);
		sts.addStyleNames(ValoTheme.MENUBAR_SMALL, ValoTheme.BUTTON_BORDERLESS, "clearDisabled");
		updateInfo =  new Button("Update Information",VaadinIcons.EXCLAMATION);
		updateInfo.addStyleNames(ValoTheme.MENUBAR_SMALL);
		//updateInfo.setDisableOnClick(true);
		updateInfo.addClickListener(event -> {
			//chestionar window
			Window  up =  new UpdateInfo(db,this);
			up.setModal(true);
			up.center();
			UI.getCurrent().addWindow(up);
		});
		stsLayout.addComponents(sts,updateInfo);
		stsLayout.setComponentAlignment(sts, Alignment.MIDDLE_CENTER);
		stsLayout.setComponentAlignment(updateInfo, Alignment.MIDDLE_CENTER);
		
		//stsLayout.setWidth("100%");
		// add stylename
		page.addComponent(stsLayout);
		page.setComponentAlignment(stsLayout, Alignment.MIDDLE_CENTER);
	//	}
		/*if (myinfo != null && Integer.parseInt(VaadinSession.getCurrent().getAttribute(AttNames.ISQUPDATED.getAtt()).toString())<1) {
			stsLayout.setVisible(true);
		} else
			stsLayout.setVisible(false);
*/
		///

		nameTitle = new HorizontalLayout();
		nameTitleLabel = new Button("Name, Gender and Country Information", VaadinIcons.INFO_CIRCLE_O);
		nameTitleLabel.setEnabled(false);
		nameTitleLabel.addStyleNames(ValoTheme.BUTTON_BORDERLESS, ValoTheme.MENU_ROOT, "userIcon", "clearDisabled");
		nameTitle.addComponent(nameTitleLabel);
		nameTitle.setComponentAlignment(nameTitleLabel, Alignment.MIDDLE_CENTER);
		page.addComponent(nameTitle);
		page.setComponentAlignment(nameTitle, Alignment.MIDDLE_CENTER);

		fullNamelayout = new HorizontalLayout();
		firstNameLayout = new CssLayout();
		firstNameLabel = new Button("First Name - ");
		firstNameLabel.setEnabled(false);
		firstNameLabel.addStyleNames(ValoTheme.BUTTON_BORDERLESS, "userIcon", "clearDisabled");
	
		firstName = new Label();

		firstNameLayout.addComponents(firstNameLabel, firstName);
		firstNameLayout.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		lastNameLayout = new CssLayout();

		lastNameLabel = new Button("Last Name - ");
		lastNameLabel.setEnabled(false);
		lastNameLabel.addStyleNames(ValoTheme.BUTTON_BORDERLESS, "userIcon", "clearDisabled");
		lastName = new Label();

		lastNameLayout.addComponents(lastNameLabel, lastName);
		lastNameLayout.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		sexLayout = new CssLayout();
		sexLabel = new Button("Gender - ");
		sexLabel.setEnabled(false);
		sexLabel.addStyleNames(ValoTheme.BUTTON_BORDERLESS, "userIcon", "clearDisabled");
		sex = new Label();
		/*
		 * if(!isSet) { sex.setCaption("N/A");
		 * sex.setDescription("Will be updated upon vising the donation centre!"); }
		 */
		// else sex.setCaption(genderAndCountry.get(0));

		sexLayout.addComponents(sexLabel, sex);
		sexLayout.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

		countryLayout = new CssLayout();
		countryLabel = new Button("Country - ");
		countryLabel.setEnabled(false);
		countryLabel.addStyleNames(ValoTheme.BUTTON_BORDERLESS, "userIcon", "clearDisabled");

		country = new Label();
		/*
		 * if(!isSet) { country.setCaption("N/A");
		 * country.setDescription("Will be updated upon vising the donation centre!");
		 * }//else country.setCaption(genderAndCountry.get(0));
		 */

		countryLayout.addComponents(countryLabel, country);
		countryLayout.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

		fullNamelayout.addComponents(firstNameLayout, lastNameLayout, sexLayout, countryLayout);
		fullNamelayout.setComponentAlignment(firstNameLayout, Alignment.MIDDLE_CENTER);
		fullNamelayout.setComponentAlignment(lastNameLayout, Alignment.MIDDLE_CENTER);
		fullNamelayout.setComponentAlignment(sexLayout, Alignment.MIDDLE_CENTER);
		fullNamelayout.setComponentAlignment(countryLayout, Alignment.MIDDLE_CENTER);
		fullNamelayout.setMargin(false);

		fullNamelayout.setSizeFull();
		page.addComponent(fullNamelayout);
		page.setComponentAlignment(fullNamelayout, Alignment.MIDDLE_CENTER);
		//

		bloodInfoTitle = new HorizontalLayout();

		bloodInfoLabel = new Button("Blood Donation Information", new ThemeResource("bloodDrop.png"));
		bloodInfoLabel.setEnabled(false);
		bloodInfoLabel.addStyleNames(ValoTheme.BUTTON_BORDERLESS, ValoTheme.MENU_ROOT, "userIcon", "clearDisabled");
		bloodInfoTitle.addComponent(bloodInfoLabel);

		bloodInfoTitle.setComponentAlignment(bloodInfoLabel, Alignment.MIDDLE_CENTER);
		page.addComponent(bloodInfoTitle);
		page.setComponentAlignment(bloodInfoTitle, Alignment.MIDDLE_CENTER);

		// new
		eligibleLayout = new HorizontalLayout();
		eligibleContainer = new CssLayout();
		eligible = new Button("Eligible -");
		eligible.setEnabled(false);
		eligible.addStyleNames(ValoTheme.BUTTON_BORDERLESS, "clearDisabled");
		eligibleVal = new Label();
		eligibleVal.setSizeUndefined();
		eligibleContainer.addComponents(eligible, eligibleVal);
		eligibleContainer.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);
		eligibleContainer.setSizeUndefined();
		eligibleLayout.addComponent(eligibleContainer);
		eligibleLayout.setComponentAlignment(eligibleContainer, Alignment.MIDDLE_CENTER);
		eligibleLayout.setMargin(false);

		page.addComponent(eligibleLayout);
		page.setComponentAlignment(eligibleLayout, Alignment.MIDDLE_CENTER);

		// new

		bloodInfo = new HorizontalLayout();
		bloodTypeLayout = new CssLayout();
		bloodTypeLabel = new Button("Blood Type -");
		bloodTypeLabel.setEnabled(false);
		bloodTypeLabel.addStyleNames(ValoTheme.BUTTON_BORDERLESS, "userIcon", "clearDisabled");
		bloodType = new Label();
		bloodTypeLayout.addComponents(bloodTypeLabel, bloodType);
		bloodTypeLayout.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

		bloodQuantityDonated = new CssLayout();
		quantityTitle = new Button("Total Quantity Donated -");
		quantityTitle.setEnabled(false);
		quantityTitle.addStyleNames(ValoTheme.BUTTON_BORDERLESS, "userIcon", "clearDisabled");
		quantity = new Label();

		bloodQuantityDonated.addComponents(quantityTitle, quantity);
		bloodQuantityDonated.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

		lastDonationDateLayout = new CssLayout();
		lastDonationDateTitle = new Button("Last Donation Date -", VaadinIcons.CALENDAR_BRIEFCASE);
		lastDonationDateTitle.setEnabled(false);
		lastDonationDateTitle.addStyleNames(ValoTheme.BUTTON_BORDERLESS, "userIcon", "clearDisabled");

		lastDonationDate = new Label();
		lastDonationDateLayout.setSizeUndefined();
		lastDonationDateLayout.addComponents(lastDonationDateTitle, lastDonationDate);
		lastDonationDateLayout.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

		nextDonationDateLayout = new CssLayout();
		nextDonationDateTitle = new Button("Next Eligible Donation Date -", VaadinIcons.CALENDAR_CLOCK);
		nextDonationDateTitle.setEnabled(false);
		nextDonationDateTitle.addStyleNames(ValoTheme.BUTTON_BORDERLESS, "userIcon", "clearDisabled");

		// TODO need period from yodatime !!!!!! min 70 zile de la donare
		nextDonationDate = new Label("N/A");

		nextDonationDateLayout.addComponents(nextDonationDateTitle, nextDonationDate);
		nextDonationDateLayout.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

		//

		bloodInfo.addComponents(bloodTypeLayout, bloodQuantityDonated, lastDonationDateLayout, nextDonationDateLayout);
		bloodInfo.setComponentAlignment(bloodTypeLayout, Alignment.MIDDLE_LEFT);
		bloodInfo.setComponentAlignment(bloodQuantityDonated, Alignment.MIDDLE_LEFT);
		bloodInfo.setComponentAlignment(lastDonationDateLayout, Alignment.MIDDLE_LEFT);
		bloodInfo.setComponentAlignment(nextDonationDateLayout, Alignment.MIDDLE_LEFT);
		bloodInfo.setMargin(false);
		bloodInfo.setSpacing(false);
		//bloodInfo.setSizeUndefined();
		//bloodInfo.setSizeFull();
		// bloodInfo.addStyleName("myBloodInfoMargin1");
		content1.setContent(bloodInfo);
		content1.addStyleName(ValoTheme.PANEL_WELL);

	/*	page.addComponent(bloodInfo);
		page.setComponentAlignment(bloodInfo, Alignment.MIDDLE_LEFT);*/
		page.addComponent(content1);
		page.setComponentAlignment(content1, Alignment.MIDDLE_LEFT);

		//
		medicalUnitTitle = new HorizontalLayout();
		medicalUnitLabel = new Label("Medical Facility Information");
		medicalUnitTitle.addComponent(medicalUnitLabel);

		medicalUnitTitle.setComponentAlignment(medicalUnitLabel, Alignment.MIDDLE_CENTER);
		page.addComponent(medicalUnitTitle);
		page.setComponentAlignment(medicalUnitTitle, Alignment.MIDDLE_CENTER);

		page.setMargin(false);

		page.addStyleName(ValoTheme.PANEL_WELL);
		isQUpdated();
		updateQInfo(db);
		setCompositionRoot(page);

	}
	
	public void isQUpdated() {
		System.out.println("isQUpdated: "+VaadinSession.getCurrent().getAttribute(AttNames.ISQUPDATED.getAtt()));
		if(VaadinSession.getCurrent().getAttribute(AttNames.ISQUPDATED.getAtt())!= null)
			if(Integer.parseInt(VaadinSession.getCurrent().getAttribute(AttNames.ISQUPDATED.getAtt()).toString())>0) {
			page.removeComponent(stsLayout);
			}
		}
	public void updateQInfo(BloodBankDatabase db) {
		Map<String, String> myinfo = db.getMyInfo(VaadinSession.getCurrent().getAttribute(AttNames.USERID.getAtt()).toString());
		if (myinfo == null) {
			Notification notif = new Notification("DB transaction errror. wait for db to come back up!",
					Notification.Type.ERROR_MESSAGE);
			notif.setPosition(Position.BOTTOM_RIGHT);
			notif.setDelayMsec(15000);

			notif.show(Page.getCurrent());
		}else {
			sex.setCaption(myinfo.get("gender"));
			country.setCaption(myinfo.get("country"));
			eligibleVal.setCaption(myinfo.get("eligible"));
			bloodType.setCaption(myinfo.get("bloodtype"));
			quantity.setCaption(myinfo.get("totalblood"));
			lastDonationDate.setCaption(myinfo.get("lastdonationdate"));
		}
		
		List<String> name = db.getFullName(VaadinSession.getCurrent().getAttribute(AttNames.USERID.getAtt()).toString());
		if (name == null || name.isEmpty()) {
			Notification notif = new Notification("Error Ocurred !", Notification.Type.HUMANIZED_MESSAGE);
			notif.setPosition(Position.BOTTOM_RIGHT);
			notif.setDelayMsec(4000);
			notif.show(Page.getCurrent());
			name.add("N/A");
			name.add("N/A");
		}
		firstName.setCaption(name.get(0));
		lastName.setCaption(name.get(1));
		
	

	}
		
	

}
