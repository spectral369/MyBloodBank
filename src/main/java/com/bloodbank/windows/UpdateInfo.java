package com.bloodbank.windows;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.bloodbank.database.BloodBankDatabase;
import com.bloodbank.front.MyInfo;
import com.bloodbank.utils.AttNames;
import com.vaadin.data.Binder;
import com.vaadin.data.ValueProvider;
import com.vaadin.data.validator.EmailValidator;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.server.Setter;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.window.WindowMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.CheckBox;
import com.vaadin.ui.CheckBoxGroup;
import com.vaadin.ui.ComboBox;
import com.vaadin.ui.DateField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Panel;
import com.vaadin.ui.RadioButtonGroup;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;

public class UpdateInfo extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private VerticalLayout root;

	private HorizontalLayout titleLayout;

	private Button title;

	private HorizontalLayout sexVarstaMediuNationalitateLayout;

	private VerticalLayout sexLayout;

	private RadioButtonGroup<String> sexChoise;

	private VerticalLayout varstaLayout;

	private Binder<UpdateInfo> binder;

	private VerticalLayout mediuLayout;

	private RadioButtonGroup<String> mediuChoise;

	private VerticalLayout nationalitateLayout;

	private ComboBox<String> nationalitateChoise;
	private DateField birthdate;

	private HorizontalLayout stareTratamentDroguriSchimbarePartenerLayout;

	private VerticalLayout stareLayout;

	private RadioButtonGroup<String> stareChoise;

	private VerticalLayout tratamentLayout;

	private RadioButtonGroup<String> tratamentChoise;

	private VerticalLayout droguriLayout;

	private RadioButtonGroup<String> droguriChoise;

	private VerticalLayout schimbarePartenerLayout;

	private RadioButtonGroup<String> schimbarePartenerChoise;
	private Panel content1;
	private Panel content2;

	private HorizontalLayout evenimenteStrainatateBolifumatorLayout;

	private VerticalLayout evenimenteLayout;

	private CheckBoxGroup<String> evenimenteChoise;

	private VerticalLayout strainatateLayout;

	private RadioButtonGroup<String> strainatateChoise;

	private VerticalLayout boliLayout;

	private CheckBoxGroup<String> boliChoise;

	private VerticalLayout fumatorLayout;

	private RadioButtonGroup<String> fumatorChoise;

	private HorizontalLayout termsLayout;

	private CheckBox infoTerms;

	private HorizontalLayout btnLayout;

	private Button cancel;

	private Button updateInfo;
	private VerticalLayout alcohoolLayout;
	private RadioButtonGroup<String> alcohoolChoise;

	private HorizontalLayout emailTaraJudetLayout;

	private VerticalLayout emailLayout;

	private TextField emailField;

	private VerticalLayout taraLayout;

	private ComboBox<String> taraChoise;

	private VerticalLayout judetLayout;

	private ComboBox<String> judetChoise;

	public UpdateInfo(BloodBankDatabase db,MyInfo info) {

		root = new VerticalLayout();

		content1 = new Panel();
		content1.setSizeFull();
		content2 = new Panel();
		content2.setSizeFull();

		binder = new Binder<>();

		titleLayout = new HorizontalLayout();
		titleLayout.setWidth("100%");
		titleLayout.addStyleName(ValoTheme.MENU_ROOT);
		title = new Button("Questionnaire", VaadinIcons.QUESTION_CIRCLE_O);
		title.setEnabled(false);
		title.addStyleNames("clearDisabled", ValoTheme.BUTTON_BORDERLESS);
		titleLayout.addComponent(title);
		titleLayout.setComponentAlignment(title, Alignment.MIDDLE_CENTER);
		root.addComponent(titleLayout);
		root.setComponentAlignment(titleLayout, Alignment.MIDDLE_CENTER);

		sexVarstaMediuNationalitateLayout = new HorizontalLayout();
		sexVarstaMediuNationalitateLayout.setMargin(false);
		sexVarstaMediuNationalitateLayout.setSpacing(false);
		sexLayout = new VerticalLayout();
		sexChoise = new RadioButtonGroup<>("Gender");
		sexChoise.setItems("M", "F");
		sexChoise.setRequiredIndicatorVisible(true);
		sexLayout.addComponent(sexChoise);
		binder.forField(sexChoise).asRequired()
		.bind(new ValueProvider<UpdateInfo, String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String apply(UpdateInfo source) {
				return null;
			}
		}, new Setter<UpdateInfo, String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void accept(UpdateInfo bean, String fieldvalue) {

			}
		});
		sexLayout.setComponentAlignment(sexChoise, Alignment.MIDDLE_CENTER);
		sexVarstaMediuNationalitateLayout.addComponent(sexLayout);
		sexVarstaMediuNationalitateLayout.setComponentAlignment(sexLayout, Alignment.TOP_CENTER);

		varstaLayout = new VerticalLayout();

		birthdate = new DateField("Birth date");
		birthdate.setDateFormat("dd-MM-yyyy");
		birthdate.setRequiredIndicatorVisible(true);

		binder.forField(birthdate).asRequired().bind(new ValueProvider<UpdateInfo, LocalDate>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public LocalDate apply(UpdateInfo source) {
				return null;
			}
		}, new Setter<UpdateInfo, LocalDate>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void accept(UpdateInfo bean, LocalDate fieldvalue) {

			}
		});
		varstaLayout.addComponents(birthdate);
		varstaLayout.setComponentAlignment(birthdate, Alignment.MIDDLE_CENTER);

		sexVarstaMediuNationalitateLayout.addComponent(varstaLayout);
		sexVarstaMediuNationalitateLayout.setComponentAlignment(varstaLayout, Alignment.TOP_CENTER);

		mediuLayout = new VerticalLayout();
		mediuChoise = new RadioButtonGroup<>("Enviroment");
		mediuChoise.setItems("Rural", "Urban");
		mediuChoise.setRequiredIndicatorVisible(true);
		binder.forField(mediuChoise).asRequired()
		.bind(new ValueProvider<UpdateInfo, String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String apply(UpdateInfo source) {
				return null;
			}
		}, new Setter<UpdateInfo, String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void accept(UpdateInfo bean, String fieldvalue) {

			}
		});
		mediuLayout.addComponent(mediuChoise);
		mediuLayout.setComponentAlignment(mediuChoise, Alignment.MIDDLE_CENTER);
		sexVarstaMediuNationalitateLayout.addComponent(mediuLayout);
		sexVarstaMediuNationalitateLayout.setComponentAlignment(mediuLayout, Alignment.TOP_CENTER);

		nationalitateLayout = new VerticalLayout();
		nationalitateChoise = new ComboBox<>("Nationality");
		nationalitateChoise.setRequiredIndicatorVisible(true);
		List<String> items = new ArrayList<>();
		items.add("Romana");
		items.add("Maghiara");
		items.add("Sarba");
		items.add("Croata");
		items.add("Bulgara");
		items.add("Alta");
		nationalitateChoise.setItems(items);
		nationalitateChoise.setEmptySelectionAllowed(false);
		nationalitateChoise.setSelectedItem(items.get(0));
		nationalitateChoise.setTextInputAllowed(false);
		binder.forField(nationalitateChoise).asRequired()
		.bind(new ValueProvider<UpdateInfo, String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String apply(UpdateInfo source) {
				return null;
			}
		}, new Setter<UpdateInfo, String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void accept(UpdateInfo bean, String fieldvalue) {

			}
		});
		nationalitateLayout.addComponent(nationalitateChoise);
		nationalitateLayout.setComponentAlignment(nationalitateChoise, Alignment.MIDDLE_CENTER);
		sexVarstaMediuNationalitateLayout.addComponent(nationalitateLayout);
		sexVarstaMediuNationalitateLayout.setComponentAlignment(nationalitateLayout, Alignment.TOP_CENTER);

		root.addComponent(sexVarstaMediuNationalitateLayout);
		root.setComponentAlignment(sexVarstaMediuNationalitateLayout, Alignment.MIDDLE_CENTER);

		emailTaraJudetLayout = new HorizontalLayout();
		emailLayout = new VerticalLayout();
		emailField = new TextField("E-Mail:");
		emailField.addStyleName(ValoTheme.TEXTFIELD_ALIGN_CENTER);
		emailField.setRequiredIndicatorVisible(true);
		binder.forField(emailField)
		.withValidator(new EmailValidator("E-Mail is not valid!"))
		.withValidator(str -> db.checkEmail(emailField.getValue().trim()) != 1,
				"E-Mail address is already in use!")
		.bind(new ValueProvider<UpdateInfo, String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String apply(UpdateInfo source) {
				return null;
			}
		}, new Setter<UpdateInfo, String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void accept(UpdateInfo bean, String fieldvalue) {

			}
		});
		emailLayout.addComponent(emailField);
		emailLayout.setComponentAlignment(emailField, Alignment.MIDDLE_CENTER);
		emailTaraJudetLayout.addComponent(emailLayout);
		emailTaraJudetLayout.setComponentAlignment(emailLayout, Alignment.TOP_CENTER);
		taraLayout = new VerticalLayout();
		taraChoise = new ComboBox<>("Tara");
		taraChoise.setItems("Romania");
		taraChoise.setEmptySelectionAllowed(false);
		binder.forField(taraChoise).asRequired()
		.bind(new ValueProvider<UpdateInfo, String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String apply(UpdateInfo source) {
				return null;
			}
		}, new Setter<UpdateInfo, String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void accept(UpdateInfo bean, String fieldvalue) {

			}
		});
		taraChoise.setTextInputAllowed(false);
		taraChoise.setRequiredIndicatorVisible(true);
		taraLayout.addComponent(taraChoise);
		taraLayout.setComponentAlignment(taraChoise, Alignment.MIDDLE_CENTER);
		emailTaraJudetLayout.addComponent(taraLayout);
		emailTaraJudetLayout.setComponentAlignment(taraLayout, Alignment.TOP_CENTER);

		judetLayout = new VerticalLayout();
		judetChoise = new ComboBox<>("Judet");
		judetChoise.setItems("Timis");
		judetChoise.setEmptySelectionAllowed(false);

		judetChoise.setTextInputAllowed(false);
		judetChoise.setRequiredIndicatorVisible(true);
		binder.forField(judetChoise).asRequired()
		.bind(new ValueProvider<UpdateInfo, String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String apply(UpdateInfo source) {
				return null;
			}
		}, new Setter<UpdateInfo, String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void accept(UpdateInfo bean, String fieldvalue) {

			}
		});
		judetLayout.addComponent(judetChoise);
		judetLayout.setComponentAlignment(judetChoise, Alignment.MIDDLE_CENTER);
		emailTaraJudetLayout.addComponent(judetLayout);
		emailTaraJudetLayout.setComponentAlignment(judetLayout, Alignment.TOP_CENTER);
		root.addComponent(emailTaraJudetLayout);
		root.setComponentAlignment(emailTaraJudetLayout, Alignment.MIDDLE_CENTER);

		stareTratamentDroguriSchimbarePartenerLayout = new HorizontalLayout();
		stareTratamentDroguriSchimbarePartenerLayout.setSpacing(false);
		stareTratamentDroguriSchimbarePartenerLayout.setMargin(false);

		stareLayout = new VerticalLayout();
		stareChoise = new RadioButtonGroup<>("Considerati ca aveti o stare buna de sanatate?");
		stareChoise.setItems("Da", "Nu");
		stareChoise.setRequiredIndicatorVisible(true);
		binder.forField(stareChoise).asRequired()
		.bind(new ValueProvider<UpdateInfo, String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String apply(UpdateInfo source) {
				return null;
			}
		}, new Setter<UpdateInfo, String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void accept(UpdateInfo bean, String fieldvalue) {

			}
		});
		stareLayout.addComponent(stareChoise);
		stareLayout.setComponentAlignment(stareChoise, Alignment.MIDDLE_CENTER);
		stareTratamentDroguriSchimbarePartenerLayout.addComponent(stareLayout);
		stareTratamentDroguriSchimbarePartenerLayout.setComponentAlignment(stareLayout, Alignment.TOP_CENTER);

		tratamentLayout = new VerticalLayout();
		tratamentChoise = new RadioButtonGroup<>("Urmati un tratament medicamentos?");
		tratamentChoise.setItems("Da", "Nu");
		tratamentChoise.setRequiredIndicatorVisible(true);
		binder.forField(tratamentChoise).asRequired()
		.bind(new ValueProvider<UpdateInfo, String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String apply(UpdateInfo source) {
				return null;
			}
		}, new Setter<UpdateInfo, String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void accept(UpdateInfo bean, String fieldvalue) {

			}
		});
		tratamentLayout.addComponent(tratamentChoise);
		tratamentLayout.setComponentAlignment(tratamentChoise, Alignment.MIDDLE_CENTER);
		stareTratamentDroguriSchimbarePartenerLayout.addComponent(tratamentLayout);
		stareTratamentDroguriSchimbarePartenerLayout.setComponentAlignment(tratamentLayout, Alignment.TOP_CENTER);

		droguriLayout = new VerticalLayout();
		droguriChoise = new RadioButtonGroup<>("V-aţi injectat vreodată droguri?");
		droguriChoise.setItems("Da", "Nu");
		droguriChoise.setRequiredIndicatorVisible(true);
		binder.forField(droguriChoise).asRequired()
		.bind(new ValueProvider<UpdateInfo, String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String apply(UpdateInfo source) {
				return null;
			}
		}, new Setter<UpdateInfo, String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void accept(UpdateInfo bean, String fieldvalue) {

			}
		});
		droguriLayout.addComponent(droguriChoise);
		droguriLayout.setComponentAlignment(droguriChoise, Alignment.MIDDLE_CENTER);
		stareTratamentDroguriSchimbarePartenerLayout.addComponent(droguriLayout);
		stareTratamentDroguriSchimbarePartenerLayout.setComponentAlignment(droguriLayout, Alignment.TOP_CENTER);

		schimbarePartenerLayout = new VerticalLayout();
		schimbarePartenerChoise = new RadioButtonGroup<>("Aţi schimbat partenerul (partenera) în ultimele 6 luni?");
		schimbarePartenerChoise.setItems("Da", "Nu");
		schimbarePartenerChoise.setRequiredIndicatorVisible(true);
		binder.forField(schimbarePartenerChoise).asRequired()
		.bind(new ValueProvider<UpdateInfo, String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String apply(UpdateInfo source) {
				return null;
			}
		}, new Setter<UpdateInfo, String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void accept(UpdateInfo bean, String fieldvalue) {

			}
		});
		schimbarePartenerLayout.addComponent(schimbarePartenerChoise);
		schimbarePartenerLayout.setComponentAlignment(schimbarePartenerChoise, Alignment.MIDDLE_CENTER);
		stareTratamentDroguriSchimbarePartenerLayout.addComponent(schimbarePartenerLayout);
		stareTratamentDroguriSchimbarePartenerLayout.setComponentAlignment(schimbarePartenerLayout,
				Alignment.TOP_CENTER);
		content1.setContent(stareTratamentDroguriSchimbarePartenerLayout);

		root.addComponent(content1);
		root.setComponentAlignment(content1, Alignment.MIDDLE_CENTER);

		evenimenteStrainatateBolifumatorLayout = new HorizontalLayout();

		evenimenteLayout = new VerticalLayout();
		evenimenteChoise = new CheckBoxGroup<>("De la ultima donare, sau în ultimele 12 luni aţi suferit:");
		evenimenteChoise.setItems("o intervenţie chirurgicală sau investigaţii medicale?",
				"tatuaje, acupunctură, găuri pentru cercei?", "aţi fost transfuzat (a) ?", "aţi fost însărcinată?","Nimic de mai sus");
		evenimenteChoise.setRequiredIndicatorVisible(true);
		binder.forField(evenimenteChoise).asRequired()
		.bind(new ValueProvider<UpdateInfo, Set<String>>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Set<String> apply(UpdateInfo source) {
				return null;
			}
		}, new Setter<UpdateInfo, Set<String>>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void accept(UpdateInfo bean, Set<String> fieldvalue) {

			}
		});
		evenimenteLayout.addComponent(evenimenteChoise);
		evenimenteLayout.setComponentAlignment(evenimenteChoise, Alignment.MIDDLE_CENTER);
		evenimenteStrainatateBolifumatorLayout.addComponent(evenimenteLayout);
		evenimenteStrainatateBolifumatorLayout.setComponentAlignment(evenimenteLayout, Alignment.TOP_CENTER);

		strainatateLayout = new VerticalLayout();
		strainatateChoise = new RadioButtonGroup<>("A/ti calatorit in ultimele 24 de luni in strainatate?");
		strainatateChoise.setItems("Da", "Nu");
		strainatateChoise.setRequiredIndicatorVisible(true);
		binder.forField(strainatateChoise).asRequired()
		.bind(new ValueProvider<UpdateInfo, String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String apply(UpdateInfo source) {
				return null;
			}
		}, new Setter<UpdateInfo, String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void accept(UpdateInfo bean, String fieldvalue) {

			}
		});
		strainatateLayout.addComponent(strainatateChoise);
		strainatateLayout.setComponentAlignment(strainatateChoise, Alignment.MIDDLE_CENTER);
		evenimenteStrainatateBolifumatorLayout.addComponent(strainatateLayout);
		evenimenteStrainatateBolifumatorLayout.setComponentAlignment(strainatateLayout, Alignment.TOP_CENTER);

		boliLayout = new VerticalLayout();
		boliChoise = new CheckBoxGroup<>("Aţi suferit vreodată de:");
		boliChoise.setItems("icter, tuberculoză, febră reumatică, malarie?",
				"boli de inimă, tensiune arterială mare sau mică?", "boli transmise sexual (hiv, sifilis etc)",
				"accidente vasculare cardiace sau cerebrale?", "convulsii, boli nervoase?",
				"boli cronice (diabet, ulcer, cancer, astm) ?",
				"Nimic de mai sus");
		boliChoise.setRequiredIndicatorVisible(true);
		boliLayout.addComponent(boliChoise);
		binder.forField(boliChoise).asRequired()
		.bind(new ValueProvider<UpdateInfo, Set<String>>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Set<String> apply(UpdateInfo source) {
				return null;
			}
		}, new Setter<UpdateInfo, Set<String>>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void accept(UpdateInfo bean, Set<String> fieldvalue) {

			}
		});
		boliLayout.setComponentAlignment(boliChoise, Alignment.MIDDLE_CENTER);
		evenimenteStrainatateBolifumatorLayout.addComponent(boliLayout);
		evenimenteStrainatateBolifumatorLayout.setComponentAlignment(boliLayout, Alignment.TOP_CENTER);

		fumatorLayout = new VerticalLayout();
		fumatorChoise = new RadioButtonGroup<>("Sunteti fumator:");
		fumatorChoise.setItems("Da", "Nu");
		fumatorChoise.setRequiredIndicatorVisible(true);
		binder.forField(fumatorChoise).asRequired()
		.bind(new ValueProvider<UpdateInfo, String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String apply(UpdateInfo source) {
				return null;
			}
		}, new Setter<UpdateInfo, String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void accept(UpdateInfo bean, String fieldvalue) {

			}
		});
		fumatorLayout.addComponent(fumatorChoise);
		fumatorLayout.setComponentAlignment(fumatorChoise, Alignment.MIDDLE_CENTER);
		evenimenteStrainatateBolifumatorLayout.addComponent(fumatorLayout);
		evenimenteStrainatateBolifumatorLayout.setComponentAlignment(fumatorLayout, Alignment.TOP_CENTER);

		alcohoolLayout = new VerticalLayout();
		alcohoolChoise = new RadioButtonGroup<>("A-ti consumat alcohol recent:");
		alcohoolChoise.setItems("Da", "Nu");
		alcohoolChoise.setRequiredIndicatorVisible(true);
		binder.forField(alcohoolChoise).asRequired()
		.bind(new ValueProvider<UpdateInfo, String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public String apply(UpdateInfo source) {
				return null;
			}
		}, new Setter<UpdateInfo, String>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void accept(UpdateInfo bean, String fieldvalue) {

			}
		});
		alcohoolLayout.addComponent(alcohoolChoise);
		alcohoolLayout.setComponentAlignment(alcohoolChoise, Alignment.MIDDLE_CENTER);
		evenimenteStrainatateBolifumatorLayout.addComponent(alcohoolLayout);
		evenimenteStrainatateBolifumatorLayout.setComponentAlignment(alcohoolLayout, Alignment.TOP_CENTER);

		content2.setContent(evenimenteStrainatateBolifumatorLayout);
		root.addComponent(content2);
		root.setComponentAlignment(content2, Alignment.MIDDLE_CENTER);

		termsLayout = new HorizontalLayout();
		infoTerms = new CheckBox("Sunt responsabil de raspunsurile date!");
		infoTerms.setRequiredIndicatorVisible(true);
		binder.forField(infoTerms)
		.withValidator(val -> val == true, "You must agree with terms & conditions !")
		.bind(new ValueProvider<UpdateInfo, Boolean>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Boolean apply(UpdateInfo source) {
				return null;
			}
		}, new Setter<UpdateInfo, Boolean>() {

			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public void accept(UpdateInfo bean, Boolean fieldvalue) {

			}
		});
		
		
		termsLayout.addComponent(infoTerms);
		termsLayout.setComponentAlignment(infoTerms, Alignment.MIDDLE_CENTER);
		root.addComponent(termsLayout);
		root.setComponentAlignment(termsLayout, Alignment.MIDDLE_CENTER);

		
		
		binder.addStatusChangeListener(event -> {
			if (binder.isValid())
				updateInfo.setEnabled(true);
			else
				updateInfo.setEnabled(false);
		});
		
		btnLayout = new HorizontalLayout();
		updateInfo = new Button("Update Information", VaadinIcons.INFO);
		updateInfo.setEnabled(false);
		updateInfo.addStyleName(ValoTheme.BUTTON_PRIMARY);
		updateInfo.addClickListener(event -> {
			// stareTratamentDroguriSchimbarePartenerLayout
			Map<String, String> updateMap = new LinkedHashMap<>();

			updateMap.put("id", VaadinSession.getCurrent().getAttribute(AttNames.USERID.getAtt()).toString());
			updateMap.put("birthdate", birthdate.getValue().toString());
			updateMap.put("gender", sexChoise.getValue());
			updateMap.put("email",emailField.getValue());
			updateMap.put("country", taraChoise.getValue());
			updateMap.put("county", judetChoise.getValue());
			updateMap.put("alcohool", alcohoolChoise.getValue());
			updateMap.put("fumator", fumatorChoise.getValue());
			updateMap.put("calatorie2ani", strainatateChoise.getValue());
			updateMap.put("ultimulan", evenimenteChoise.getValue().toString());
			updateMap.put("partener", schimbarePartenerChoise.getValue());
			updateMap.put("droguri", droguriChoise.getValue());
			updateMap.put("tratament", tratamentChoise.getValue());
			updateMap.put("starebuna", stareChoise.getValue());
			updateMap.put("mediu", mediuChoise.getValue());
			updateMap.put("diseases", boliChoise.getValue().toString());
			updateMap.put("nationality", nationalitateChoise.getValue());
			System.out.println("UserID Updated: "+VaadinSession.getCurrent().getAttribute(AttNames.USERID.getAtt()).toString());
			
			int dbResponse =  db.updateMyInfo(updateMap);
			
			Notification notif = new Notification("Update Success !", Notification.Type.HUMANIZED_MESSAGE);
			notif.setPosition(Position.BOTTOM_RIGHT);
			notif.setDelayMsec(4000);
			if (dbResponse > 0) {
				VaadinSession.getCurrent().setAttribute(AttNames.ISQUPDATED.getAtt(), 1);
				notif.show(Page.getCurrent());
				info.isQUpdated();
				info.updateQInfo(db);
				
			} else {
				notif.setCaption("Update failed !");

				notif.show(Page.getCurrent());
			}
			this.close();

		});

		cancel = new Button("Cancel", VaadinIcons.CLOSE);
		cancel.addClickListener(event -> {
			this.close();
		});
		btnLayout.addComponents(updateInfo, cancel);
		btnLayout.setComponentAlignment(updateInfo, Alignment.MIDDLE_CENTER);
		btnLayout.setComponentAlignment(cancel, Alignment.MIDDLE_CENTER);
		root.addComponent(btnLayout);
		root.setComponentAlignment(btnLayout, Alignment.MIDDLE_CENTER);

		setContent(root);
		setWindowMode(WindowMode.NORMAL);
		setResizable(false);

		// setClosable(false);

		setWidth(Page.getCurrent().getBrowserWindowWidth() / 1.3f, Unit.PIXELS);
		setHeight(Page.getCurrent().getBrowserWindowHeight() / 1.3f, Unit.PIXELS);

	}
	
	
	

}
