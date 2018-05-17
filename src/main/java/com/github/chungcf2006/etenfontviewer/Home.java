package com.github.chungcf2006.etenfontviewer;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.io.IOException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Label;

public class Home {
	
	
	
	private static Image loadChar(Display display) throws IOException {
		Font font = new Font("/Users/John/git/ETen-Font-Viewer/stdfont.64", 64, 64);
		Image image = new Image(display, font.getImageData(0xB94C));
		return image;
	}
	
	public static void main(String[] args) throws IOException {
        Display display = new Display();

        Shell shell = new Shell(display);

        // the layout manager handle the layout
        // of the widgets in the container
        shell.setLayout(new FillLayout());

        //TODO add some widgets to the Shell
        
        shell.setText("倚天字型瀏覽器");

        Label label = new Label (shell, SWT.BORDER);
        label.setImage(loadChar(display));
        
        shell.open();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch()) {
                display.sleep();
            }
        }
        display.dispose();
    }
}
