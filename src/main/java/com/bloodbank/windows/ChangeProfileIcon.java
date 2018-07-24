package com.bloodbank.windows;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import com.bloodbank.MyBloodBank.MyUI;
import com.bloodbank.database.BloodBankDatabase;
import com.bloodbank.front.CP;
import com.bloodbank.utils.AttNames;
import com.bloodbank.utils.GLobalCache;
import com.bloodbank.utils.Utils;
import com.vaadin.annotations.Push;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.FileResource;
import com.vaadin.server.Page;
import com.vaadin.server.VaadinSession;
import com.vaadin.shared.Position;
import com.vaadin.shared.ui.window.WindowMode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.Notification;
import com.vaadin.ui.UI;
import com.vaadin.ui.Upload;
import com.vaadin.ui.Upload.Receiver;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

@Push
public class ChangeProfileIcon extends Window {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private VerticalLayout root = null;
	private Button update = null;
	private HorizontalLayout iconLayout = null;
	private Image image = null;
	private HorizontalLayout uploadLayout = null;
	private ImageUploader receiver = null;
	private Upload upload = null;
	private HorizontalLayout buttonsLayout = null;
	private Button cancel = null;

	public ChangeProfileIcon(BloodBankDatabase db, CP cp, MyUI ui) {
		root = new VerticalLayout();

		iconLayout = new HorizontalLayout();
		image = new Image(null,GLobalCache.getUserIcon(Utils.nullToEmptyString(VaadinSession.getCurrent().getAttribute(AttNames.USERID.getAtt()))));
		image.setWidth(100, Unit.PIXELS);
		image.setHeight(70, Unit.PIXELS);
		iconLayout.addComponent(image);
		iconLayout.setComponentAlignment(image, Alignment.MIDDLE_CENTER);
		root.addComponent(iconLayout);
		root.setComponentAlignment(iconLayout, Alignment.MIDDLE_CENTER);

		uploadLayout = new HorizontalLayout();

		receiver = new ImageUploader();

		upload = new Upload("Select Image", receiver);
		upload.setImmediateMode(true);
		upload.addStartedListener(event -> {
			String type = event.getMIMEType();
			boolean allowed = false;
			for (String allow : Utils.imgAllowed()) {
				if (type.equalsIgnoreCase(allow)) {
					allowed = true;
					upload.setCaption("Image preview");
					break;
				}
			}
			if (!allowed) {
				upload.interruptUpload();
				upload.setCaption("Selected File is not supported!");
			}

		});

		upload.addSucceededListener(event -> {
			image.setSource(new FileResource(receiver.file));
			update.setEnabled(true);

		});

		uploadLayout.addComponent(upload);
		uploadLayout.setComponentAlignment(upload, Alignment.MIDDLE_CENTER);
		root.addComponent(uploadLayout);
		root.setComponentAlignment(uploadLayout, Alignment.MIDDLE_CENTER);

		buttonsLayout = new HorizontalLayout();
		update = new Button("Update", VaadinIcons.UPLOAD);
		update.setEnabled(false);
		update.addClickListener(event -> {
			try {
				File del = new File(Utils.getImagePath() + "/user_"
						+ VaadinSession.getCurrent().getAttribute(AttNames.USERID.getAtt()).toString() + ".png");

				System.out.println(del.toString() + Files.deleteIfExists(del.toPath()));

				Files.copy(receiver.file.toPath(),
						new File(Utils.getImagePath() + "/user_"
								+ VaadinSession.getCurrent().getAttribute(AttNames.USERID.getAtt()).toString() + ".png").toPath(),
						StandardCopyOption.REPLACE_EXISTING);
				GLobalCache.refreshUserIcon(Utils.nullToEmptyString(VaadinSession.getCurrent().getAttribute(AttNames.USERID.getAtt())));
				cp.setIconImg();
				ui.header.update();

			} catch (IOException e) {
				e.printStackTrace();
				Notification notif = new Notification("Error Saving File !", Notification.Type.ERROR_MESSAGE);
				notif.setPosition(Position.BOTTOM_RIGHT);
				notif.setDelayMsec(15000);
				notif.show(Page.getCurrent());
				this.close();
			}
			this.close();
			// Page.getCurrent().reload();
			UI.getCurrent().push();

		});
		cancel = new Button("Cancel", VaadinIcons.CLOSE);
		cancel.addClickListener(event -> {

			this.close();
		});
		buttonsLayout.addComponents(update, cancel);
		buttonsLayout.setComponentAlignment(update, Alignment.MIDDLE_CENTER);
		buttonsLayout.setComponentAlignment(cancel, Alignment.MIDDLE_CENTER);
		root.addComponent(buttonsLayout);
		root.setComponentAlignment(buttonsLayout, Alignment.MIDDLE_CENTER);

		setContent(root);
		setWindowMode(WindowMode.NORMAL);
		setResizable(false);

		setModal(true);
		setClosable(false);
		setWidth(Page.getCurrent().getBrowserWindowWidth() / 2, Unit.PIXELS);
		setHeight(Page.getCurrent().getBrowserWindowHeight() / 1.5f, Unit.PIXELS);

	}

}

class ImageUploader implements Receiver {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public File file = null;
	// private String path = Utils.getImagePath();

	private FileOutputStream fos;

	@Override
	public OutputStream receiveUpload(String filename, String mimeType) {
		/* File target */file = new File(System.getProperty("java.io.tmpdir") + System.getProperty("file.separator")
				+ "user_" + VaadinSession.getCurrent().getAttribute(AttNames.USERID.getAtt()).toString());

		// file = new
		// File(path+"/user_"+VaadinSession.getCurrent().getAttribute("userid").toString());
		try {
			fos = new FileOutputStream(file);

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return fos;

	}

}
