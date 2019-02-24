//Jean Remy Bougeois-Cruz

import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class SnakeMain {

	public static void main(String[] args) {
		JFrame frame = new JFrame("Snake");
		JButton sButton = new JButton("Snake");
		JButton tButton = new JButton("Tron");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setFocusable(true);
		frame.setLayout(new FlowLayout());
		SnakePanel sPanel = new SnakePanel();
		TronPanel tPanel = new TronPanel();
		frame.add(sButton);
		frame.add(tButton);

		sButton.addActionListener( new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						frame.setContentPane(sPanel);
						//Loses focus cause added this button functionality so need this
						sPanel.requestFocusInWindow();
						frame.invalidate();
						frame.validate();
						frame.pack();
						frame.setResizable(false);
					}
				});
	
		//Starts Tron Game
		tButton.addActionListener( new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) {
				frame.setContentPane(tPanel);
				tPanel.requestFocusInWindow();
				frame.invalidate();
				frame.validate();
				frame.pack();
				frame.setResizable(false);
			}
		});
		//Making it the size of our JPanel
		frame.pack();
		frame.setVisible(true);

	}
	
	
}
