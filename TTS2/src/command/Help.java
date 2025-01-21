package command;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Help implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
        	//It works in windows but i'm not sure about other operating systems
            Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler "+".\\VirtualNarratorUserGuide.pdf");
        }catch (Exception evt) {
            evt.printStackTrace();
        }
    }
}