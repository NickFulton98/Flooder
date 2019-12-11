package com.flooder;

import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Flooder
{
	JFrame mainFrame;
	JTextField searchBar;
	JButton searchButton;
	JComboBox<String> searchCriteria;
	JComboBox<String> browserCriteria;
	JLabel logo;
	
	final String[] browserOptions = {"Default"};
	final String[] searchOptions = {"Google"};
	
	ArrayList<String> searches;

	public Flooder()
	{
		searches = new ArrayList<String>();
		searches.add("this+is+a+test");
		searches.add("how+to+make+a+bunch+of+money");
		searches.add("sjsu+cs+100w+final+answer+sheet");
		searches.add("flooder+is+the+best");
		searches.add("search+in+the+light");
	}
	
	public void launch()
	{
		buildFrame();
	}
	
	private void buildFrame()
	{
		mainFrame = new JFrame("Flooder");
		mainFrame.setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		
		ImageIcon logoIcon = new ImageIcon("src/images/logo.png");
		logo = new JLabel(logoIcon);
		
		searchCriteria = new JComboBox<String>(searchOptions);
		browserCriteria = new JComboBox<String>(browserOptions);
		searchBar = new JTextField(30);
		searchButton = new JButton("Search");
		
		searchButton.addActionListener(e -> {
			try 
			{
				search();
			} 
			catch (MalformedURLException e1) 
			{
				e1.printStackTrace();
			}
		});
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 3;
		c.gridheight = 4;
		mainFrame.add(logo, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 4;
		c.gridwidth = 3;
		c.gridheight = 1;
		mainFrame.add(searchBar, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 5;
		c.gridwidth = 1;
		c.gridheight = 1;
		mainFrame.add(browserCriteria, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 5;
		c.gridwidth = 1;
		c.gridheight = 1;
		mainFrame.add(searchCriteria, c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 2;
		c.gridy = 5;
		c.gridwidth = 1;
		c.gridheight = 1;
		mainFrame.add(searchButton, c);

		mainFrame.setSize(new Dimension(400, 200));
		mainFrame.setResizable(false);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		mainFrame.setLocation(d.width/2-mainFrame.getSize().width/2, d.height/2-mainFrame.getSize().height/2);
		mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		mainFrame.setVisible(true);
	}
	
	private boolean search() throws MalformedURLException
	{
		for(int x = 0; x < searches.size(); x++)
		{
			URL fakes = new URL("https://www.google.com/search?q=" + searches.get(x));
			try
			{
				openWebpage(fakes.toURI());
			}
			catch(URISyntaxException e)
			{
				e.printStackTrace();
			}
			
		}
		
		String search = searchBar.getText().replaceAll(" ", "+");
		URL url = new URL("https://www.google.com/search?q=" + search);
		searchBar.setText("");
		
		try
		{
			return openWebpage(url.toURI());
		}
		catch(URISyntaxException e)
		{
			e.printStackTrace();
		}
		return false;
	}
	
	private boolean openWebpage(URI uri)
	{
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		
		if(desktop != null && desktop.isSupported(Desktop.Action.BROWSE))
		{
			try
			{
				desktop.browse(uri);
				return true;
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		return false;
	}
}













