package com.bloodbank.front;

import com.bloodbank.database.BloodBankDatabase;
import com.bloodbank.utils.GLobalCache;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.navigator.View;
import com.vaadin.server.FileResource;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Composite;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.themes.ValoTheme;

public class HomeView extends Composite implements View {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private VerticalLayout root = null;
	private VerticalLayout page1 = null;
	private VerticalLayout page2 = null;
	private HorizontalLayout title = null;
	private Label def = null;
	private HorizontalLayout equipAvail = null;
	private CssLayout eq1 = null;
	private Button equip = null;
	private Label equipNo = null;
	private CssLayout eq2 = null;
	private Button equip2 = null;
	private Label equipNo2 = null;
	private HorizontalLayout bloodInfoTitle = null;
	private Button totalBloodAvailableTitle = null;
	private HorizontalLayout bloodInfo = null;
	private VerticalLayout bloodGrPositive_A = null;
	private Button namePositive_A = null;
	private Label TotalPositive_ABlood = null;
	private Label TotalPositive_ABloodNo = null;
	private VerticalLayout bloodGrNegative_A = null;
	private Button nameNegative_A = null;
	private Label TotalNegative_ABlood = null;
	private Label TotalNegative_ABloodNo = null;
	private VerticalLayout bloodGrPositive_B = null;
	private Button namePositive_B = null;
	private Label TotalPositive_BBlood = null;
	private Label TotalPositive_BBloodNo = null;
	private VerticalLayout bloodGrNegative_B = null;
	private Button nameNegative_B = null;
	private Label TotalNegative_BBlood = null;
	private Label TotalNegative_BBloodNo = null;
	private VerticalLayout bloodGrPositive_O = null;
	private Button namePositive_O = null;
	private Label TotalPositive_OBlood = null;
	private Label TotalPositive_OBloodNo = null;
	private VerticalLayout bloodGrNegative_O = null;
	private Button nameNegative_O = null;
	private Label TotalNegative_OBlood = null;
	private Label TotalNegative_OBloodNo = null;
	private VerticalLayout bloodGrPositive_AB = null;
	private Button namePositive_AB = null;
	private Label TotalPositive_ABBlood = null;
	private Label TotalPositive_ABBloodNo = null;
	private VerticalLayout bloodGrNegative_AB = null;
	private Button nameNegative_AB = null;
	private Label TotalNegative_ABBlood = null;
	private Label TotalNegative_ABBloodNo = null;
	private HorizontalLayout overallInfo = null;
	private VerticalLayout totalBlood = null;
	private Button totalBloodTitle = null;
	private Label totalBloodNo = null;
	private VerticalLayout totalUsers = null;
	private Button totalUsersTitle = null;
	private Label totalUsersNo = null;
	private HorizontalLayout scheduleTitleLayout;
	private Button scheduleTitle;
	private HorizontalLayout scheduleLayout;
	private Button monday;
	private Button tuesday;
	private Button wendsday;
	private Button thurdsday;
	private Button friday;
	private Button saturday;
	private Button sunday;
	private HorizontalLayout scheduleHoursLayout;
	private Button mondayHours;
	private Button tuesdayHours;
	private Button wendsdayHours;
	private Button thurdsdayHours;
	private Button fridayHours;
	private Button saturdayHours;
	private Button sundayHours;

	public HomeView(BloodBankDatabase db) {
		root = new VerticalLayout();
		root.setSpacing(false);
		root.setMargin(false);
		page1 = new VerticalLayout();

		title = new HorizontalLayout();
		def = new Label("My Blood Bank info");
		def.setEnabled(false);

		title.addComponent(def);
		title.setComponentAlignment(def, Alignment.TOP_CENTER);

		page1.addComponent(title);
		page1.setComponentAlignment(title, Alignment.TOP_CENTER);
		equipAvail = new HorizontalLayout();
		eq1 = new CssLayout();
		equip = new Button("Equipment available: ", VaadinIcons.AMBULANCE);
		equip.setEnabled(false);
		equip.addStyleNames(ValoTheme.BUTTON_BORDERLESS, "clearDisabled");
		equipNo = new Label("2");
		equipNo.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		eq1.addComponents(equip, equipNo);
		eq1.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

		eq2 = new CssLayout();
		equip2 = new Button("Equipment free: ", VaadinIcons.REFRESH);
		equip2.setEnabled(false);
		equip2.addStyleNames(ValoTheme.BUTTON_BORDERLESS, "clearDisabled");
		equipNo2 = new Label("2");
		equipNo2.addStyleName(ValoTheme.BUTTON_BORDERLESS);
		eq2.addComponents(equip2, equipNo2);
		eq2.addStyleName(ValoTheme.LAYOUT_COMPONENT_GROUP);

		equipAvail.setWidth("100%");
		equipAvail.addComponents(eq1, eq2);
		equipAvail.setExpandRatio(eq1, 1);
		equipAvail.setExpandRatio(eq2, 1);
		equipAvail.setComponentAlignment(eq1, Alignment.MIDDLE_CENTER);
		equipAvail.setComponentAlignment(eq2, Alignment.MIDDLE_CENTER);
		page1.addComponent(equipAvail);
		page1.setComponentAlignment(equipAvail, Alignment.MIDDLE_LEFT);
		
		//schedule
		scheduleTitleLayout =  new HorizontalLayout();
		scheduleTitle =  new Button("Schedule",VaadinIcons.CLOCK);
		scheduleTitle.setEnabled(false);
		scheduleTitle.addStyleNames(ValoTheme.BUTTON_BORDERLESS,ValoTheme.ACCORDION_BORDERLESS, "clearDisabled");
		scheduleTitleLayout.addComponent(scheduleTitle);
		scheduleTitleLayout.setComponentAlignment(scheduleTitle, Alignment.MIDDLE_CENTER);
		page1.addComponent(scheduleTitleLayout);
		page1.setComponentAlignment(scheduleTitleLayout, Alignment.MIDDLE_CENTER);
		
		scheduleLayout =  new HorizontalLayout();
		scheduleLayout.setWidth("100%");
		monday =  new Button("Monday",VaadinIcons.CALENDAR_CLOCK);
		monday.setEnabled(false);
		monday.addStyleNames(ValoTheme.MENU_ROOT,ValoTheme.ACCORDION_BORDERLESS, "clearDisabled");
		tuesday =  new Button("Tuesday",VaadinIcons.CALENDAR_CLOCK);
		tuesday.setEnabled(false);
		tuesday.addStyleNames(ValoTheme.MENU_ROOT,ValoTheme.ACCORDION_BORDERLESS, "clearDisabled");
		wendsday =  new Button("Wendsday",VaadinIcons.CALENDAR_CLOCK);
		wendsday.setEnabled(false);
		wendsday.addStyleNames(ValoTheme.MENU_ROOT,ValoTheme.ACCORDION_BORDERLESS, "clearDisabled");
		thurdsday =  new Button("Thurdsday",VaadinIcons.CALENDAR_CLOCK);
		thurdsday.setEnabled(false);
		thurdsday.addStyleNames(ValoTheme.MENU_ROOT,ValoTheme.ACCORDION_BORDERLESS, "clearDisabled");
		friday =  new Button("Friday",VaadinIcons.CALENDAR_CLOCK);
		friday.setEnabled(false);
		friday.addStyleNames(ValoTheme.MENU_ROOT,ValoTheme.ACCORDION_BORDERLESS, "clearDisabled");
		saturday =  new Button("Saturday",VaadinIcons.CALENDAR_CLOCK);
		saturday.setEnabled(false);
		saturday.addStyleNames(ValoTheme.MENU_ROOT,ValoTheme.ACCORDION_BORDERLESS, "clearDisabled");
		sunday =  new Button("Sunday",VaadinIcons.CALENDAR_CLOCK);
		sunday.setEnabled(false);
		sunday.addStyleNames(ValoTheme.MENU_ROOT,ValoTheme.ACCORDION_BORDERLESS, "clearDisabled");
		scheduleLayout.addComponents(monday,tuesday,wendsday,thurdsday,friday,saturday,sunday);
		scheduleLayout.setComponentAlignment(monday, Alignment.MIDDLE_CENTER);
		scheduleLayout.setComponentAlignment(tuesday, Alignment.MIDDLE_CENTER);
		scheduleLayout.setComponentAlignment(wendsday, Alignment.MIDDLE_CENTER);
		scheduleLayout.setComponentAlignment(thurdsday, Alignment.MIDDLE_CENTER);
		scheduleLayout.setComponentAlignment(friday, Alignment.MIDDLE_CENTER);
		scheduleLayout.setComponentAlignment(saturday, Alignment.MIDDLE_CENTER);
		scheduleLayout.setComponentAlignment(sunday, Alignment.MIDDLE_CENTER);
		page1.addComponent(scheduleLayout);
		page1.setComponentAlignment(scheduleLayout, Alignment.MIDDLE_CENTER);
		
		
		scheduleHoursLayout =  new HorizontalLayout();
		scheduleHoursLayout.setWidth("100%");
		mondayHours =  new Button("9AM-4PM");
		mondayHours.setEnabled(false);
		mondayHours.addStyleNames(ValoTheme.BUTTON_BORDERLESS,"clearDisabled");
		tuesdayHours =  new Button("9AM-4PM");
		tuesdayHours.setEnabled(false);
		tuesdayHours.addStyleNames(ValoTheme.BUTTON_BORDERLESS,"clearDisabled");
		wendsdayHours =  new Button("9AM-4PM");
		wendsdayHours.setEnabled(false);
		wendsdayHours.addStyleNames(ValoTheme.BUTTON_BORDERLESS,"clearDisabled");
		thurdsdayHours =  new Button("9AM-4PM");
		thurdsdayHours.setEnabled(false);
		thurdsdayHours.addStyleNames(ValoTheme.BUTTON_BORDERLESS,"clearDisabled");
		fridayHours =  new Button("9AM-4PM");
		fridayHours.setEnabled(false);
		fridayHours.addStyleNames(ValoTheme.BUTTON_BORDERLESS,"clearDisabled");
		saturdayHours =  new Button("9AM-1PM");
		saturdayHours.setEnabled(false);
		saturdayHours.addStyleNames(ValoTheme.BUTTON_BORDERLESS,"clearDisabled");
		sundayHours =  new Button("Closed");
		sundayHours.setEnabled(false);
		sundayHours.addStyleNames(ValoTheme.BUTTON_BORDERLESS,"clearDisabled");
		scheduleHoursLayout.addComponents(mondayHours,tuesdayHours,wendsdayHours,thurdsdayHours,fridayHours,saturdayHours,sundayHours);;
		scheduleHoursLayout.setComponentAlignment(mondayHours, Alignment.MIDDLE_CENTER);
		scheduleHoursLayout.setComponentAlignment(tuesdayHours, Alignment.MIDDLE_CENTER);
		scheduleHoursLayout.setComponentAlignment(wendsdayHours, Alignment.MIDDLE_CENTER);
		scheduleHoursLayout.setComponentAlignment(thurdsdayHours, Alignment.MIDDLE_CENTER);
		scheduleHoursLayout.setComponentAlignment(fridayHours, Alignment.MIDDLE_CENTER);
		scheduleHoursLayout.setComponentAlignment(saturdayHours, Alignment.MIDDLE_CENTER);
		scheduleHoursLayout.setComponentAlignment(sundayHours, Alignment.MIDDLE_CENTER);
		page1.addComponent(scheduleHoursLayout);
		page1.setComponentAlignment(scheduleHoursLayout, Alignment.MIDDLE_CENTER);
		
		//schedule

		bloodInfoTitle = new HorizontalLayout();
		totalBloodAvailableTitle = new Button("Total Blood Available", VaadinIcons.BELL_O);
		totalBloodAvailableTitle.setEnabled(false);
		totalBloodAvailableTitle.addStyleNames(ValoTheme.BUTTON_BORDERLESS, "clearDisabled");
		bloodInfoTitle.addComponent(totalBloodAvailableTitle);
		page1.addComponent(bloodInfoTitle);
		page1.setComponentAlignment(bloodInfoTitle, Alignment.MIDDLE_CENTER);

		bloodInfo = new HorizontalLayout();
		bloodInfo.setMargin(false);
		bloodInfo.setSizeFull();
		bloodInfo.setSpacing(false);

		// Image bloodGrIcon = new Image("Blood Drop",new
		// FileResource(Utils.getImage("bloodDrop")));//safety
	//	ThemeResource bloodGrIcon = new ThemeResource("bloodDrop.png");
	
		FileResource bloodGrIcon = GLobalCache.getIcon("bloodDrop");
		// blood a+
		bloodGrPositive_A = new VerticalLayout();
		namePositive_A = new Button("A+", bloodGrIcon); // substituie cu imagine cu sange.icon.
		namePositive_A.setEnabled(false);
		namePositive_A.addStyleNames(ValoTheme.BUTTON_BORDERLESS, ValoTheme.MENU_ROOT, "userIcon", "clearDisabled");
		TotalPositive_ABlood = new Label("Total (L)");
		TotalPositive_ABloodNo = new Label("0");
		bloodGrPositive_A.addComponents(namePositive_A, TotalPositive_ABlood, TotalPositive_ABloodNo);
		// bloodGrPositive_A.setComponentAlignment(TotalPositive_ABloodNo,
		// Alignment.MIDDLE_CENTER);
		bloodInfo.addComponent(bloodGrPositive_A);

		// blood a-
		bloodGrNegative_A = new VerticalLayout();
		nameNegative_A = new Button("A-", bloodGrIcon); // substituie cu imagine cu sange.icon.
		nameNegative_A.setEnabled(false);
		nameNegative_A.addStyleNames(ValoTheme.BUTTON_BORDERLESS, ValoTheme.MENU_ROOT, "userIcon", "clearDisabled");
		TotalNegative_ABlood = new Label("Total (L)");
		TotalNegative_ABloodNo = new Label("0");
		bloodGrNegative_A.addComponents(nameNegative_A, TotalNegative_ABlood, TotalNegative_ABloodNo);
		bloodInfo.addComponent(bloodGrNegative_A);

		// blood b+
		bloodGrPositive_B = new VerticalLayout();
		namePositive_B = new Button("B+", bloodGrIcon); // substituie cu imagine cu sange.icon.
		namePositive_B.setEnabled(false);
		namePositive_B.addStyleNames(ValoTheme.BUTTON_BORDERLESS, ValoTheme.MENU_ROOT, "userIcon", "clearDisabled");
		TotalPositive_BBlood = new Label("Total (L)");
		TotalPositive_BBloodNo = new Label("0");
		bloodGrPositive_B.addComponents(namePositive_B, TotalPositive_BBlood, TotalPositive_BBloodNo);
		bloodInfo.addComponent(bloodGrPositive_B);

		// blood b-
		bloodGrNegative_B = new VerticalLayout();
		nameNegative_B = new Button("B-", bloodGrIcon); // substituie cu imagine cu sange.icon.
		nameNegative_B.setEnabled(false);
		nameNegative_B.addStyleNames(ValoTheme.BUTTON_BORDERLESS, ValoTheme.MENU_ROOT, "userIcon", "clearDisabled");
		TotalNegative_BBlood = new Label("Total (L)");
		TotalNegative_BBloodNo = new Label("0");
		bloodGrNegative_B.addComponents(nameNegative_B, TotalNegative_BBlood, TotalNegative_BBloodNo);
		bloodInfo.addComponent(bloodGrNegative_B);

		// blood O+
		bloodGrPositive_O = new VerticalLayout();
		namePositive_O = new Button("O+", bloodGrIcon); // substituie cu imagine cu sange.icon.
		namePositive_O.setEnabled(false);
		namePositive_O.addStyleNames(ValoTheme.BUTTON_BORDERLESS, ValoTheme.MENU_ROOT, "userIcon", "clearDisabled");
		TotalPositive_OBlood = new Label("Total (L)");
		TotalPositive_OBloodNo = new Label("0");
		bloodGrPositive_O.addComponents(namePositive_O, TotalPositive_OBlood, TotalPositive_OBloodNo);
		bloodInfo.addComponent(bloodGrPositive_O);

		// blood O-
		bloodGrNegative_O = new VerticalLayout();
		nameNegative_O = new Button("O-", bloodGrIcon); // substituie cu imagine cu sange.icon.
		nameNegative_O.setEnabled(false);
		nameNegative_O.addStyleNames(ValoTheme.BUTTON_BORDERLESS, ValoTheme.MENU_ROOT, "userIcon", "clearDisabled");
		TotalNegative_OBlood = new Label("Total (L)");
		TotalNegative_OBloodNo = new Label("0");
		bloodGrNegative_O.addComponents(nameNegative_O, TotalNegative_OBlood, TotalNegative_OBloodNo);
		bloodInfo.addComponent(bloodGrNegative_O);

		// blood ab+
		bloodGrPositive_AB = new VerticalLayout();
		namePositive_AB = new Button("AB+", bloodGrIcon); // substituie cu imagine cu sange.icon.
		namePositive_AB.setEnabled(false);
		namePositive_AB.addStyleNames(ValoTheme.BUTTON_BORDERLESS, ValoTheme.MENU_ROOT, "userIcon", "clearDisabled");
		TotalPositive_ABBlood = new Label("Total (L)");
		TotalPositive_ABBloodNo = new Label("0");
		bloodGrPositive_AB.addComponents(namePositive_AB, TotalPositive_ABBlood, TotalPositive_ABBloodNo);
		bloodInfo.addComponent(bloodGrPositive_AB);

		// blood AB-
		bloodGrNegative_AB = new VerticalLayout();
		nameNegative_AB = new Button("AB-", bloodGrIcon); // substituie cu imagine cu sange.icon.
		nameNegative_AB.setEnabled(false);
		nameNegative_AB.addStyleNames(ValoTheme.BUTTON_BORDERLESS, ValoTheme.MENU_ROOT, "userIcon", "clearDisabled");
		TotalNegative_ABBlood = new Label("Total (L)");
		TotalNegative_ABBloodNo = new Label("0");
		bloodGrNegative_AB.addComponents(nameNegative_AB, TotalNegative_ABBlood, TotalNegative_ABBloodNo);
		bloodInfo.addComponent(bloodGrNegative_AB);

		page1.addComponent(bloodInfo);

		page1.setMargin(false);

		page1.addStyleName(ValoTheme.PANEL_WELL);

		// part 2
		page2 = new VerticalLayout();

		overallInfo = new HorizontalLayout();
		overallInfo.setMargin(false);
		overallInfo.setSizeFull();
		overallInfo.setSpacing(false);

		totalBlood = new VerticalLayout();
		// totalBlood.addStyleName("clearDisabled");
//new ThemeResource("bloodSum.png")
		totalBloodTitle = new Button("Total Blood Donated", GLobalCache.getIcon("bloodSum")); // substituie cu

		// imagine cu

		// sange.icon.
		totalBloodTitle.setEnabled(false);
		totalBloodTitle.addStyleNames(ValoTheme.BUTTON_BORDERLESS, ValoTheme.MENU_ROOT, "bloodSum", "clearDisabled");
		// totalBloodTitle.removeStyleName("v-disabled");
		totalBloodNo = new Label("0");
		totalBlood.addComponents(totalBloodTitle, totalBloodNo);
		totalBlood.setComponentAlignment(totalBloodNo, Alignment.MIDDLE_CENTER);
		totalBlood.setComponentAlignment(totalBloodTitle, Alignment.MIDDLE_CENTER);
		overallInfo.addComponent(totalBlood);

		totalUsers = new VerticalLayout();
		totalUsersTitle = new Button("Total users", VaadinIcons.USERS);
		totalUsersTitle.setEnabled(false);
		totalUsersTitle.addStyleNames(ValoTheme.BUTTON_BORDERLESS, ValoTheme.MENU_ROOT, "clearDisabled");
		totalUsersNo = new Label(String.valueOf(db.getUserCount()));// only updates on refresh

		totalUsers.addComponents(totalUsersTitle, totalUsersNo);
		totalUsers.setComponentAlignment(totalUsersTitle, Alignment.MIDDLE_CENTER);
		totalUsers.setComponentAlignment(totalUsersNo, Alignment.MIDDLE_CENTER);
		overallInfo.addComponent(totalUsers);

		page2.addComponent(overallInfo);

		page2.setMargin(false);

		page2.addStyleName(ValoTheme.PANEL_WELL);
		root.addComponents(page1, page2);

		setCompositionRoot(root);

	}

}
