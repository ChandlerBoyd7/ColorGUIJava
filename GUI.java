import java.awt.*;
import java.awt.event.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import java.io.*;

public class GUI extends JFrame implements ActionListener {
	
	GData[] data;
	int index = -1;
	
	JPanel jpBase, jpTop, jpActions, jpList, jpBottom;
	JLabel jl0, jl1, jl2, jl3, jlError;
	JTextField jtf0, jtf1, jtf2, jtf3;
	JButton jbCreate, jbNext, jbPrevious, jbList, jbSave, jbLoad;
	JTextArea jtaData;
	JDialog jdError;
	
	GUI() {
		setTitle("GUI");
		setSize(800, 600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		jpBase = new JPanel();
		jpTop = new JPanel();
		jpActions = new JPanel();
		jpList = new JPanel();
		jpBottom = new JPanel();
		System.out.println("Chandler Boyd");
		jl0 = new JLabel("Name");
		jl1 = new JLabel("Red");
		jl2 = new JLabel("Green");
		jl3 = new JLabel("Blue");
		
		jtf0 = new JTextField();
		jtf1 = new JTextField(6);
		jtf2 = new JTextField(6);
		jtf3 = new JTextField(6);
		
		jbCreate = new JButton("Add");
		jbCreate.addActionListener(new ActionListener() { 
			
			public void actionPerformed(ActionEvent e) { 
				create();
			} 
		});
		jbNext = new JButton("Next");
		jbNext.addActionListener(new ActionListener() { 
			
			public void actionPerformed(ActionEvent e) { 
				next();
			} 
		});
		jbPrevious = new JButton("Prevoius");
		jbPrevious.addActionListener(new ActionListener() { 
			
			public void actionPerformed(ActionEvent e) { 
				previous();
			} 
		});
		jbList = new JButton("List");
		jbList.addActionListener(new ActionListener() { 
			
			public void actionPerformed(ActionEvent e) { 
				list();
			} 
		});
		jbSave = new JButton("Save");
		jbSave.addActionListener(new ActionListener() { 
			
			public void actionPerformed(ActionEvent e) { 
				try {
					save();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} 
		});
		jbLoad = new JButton("Load");
		jbLoad.addActionListener(new ActionListener() { 
			
			public void actionPerformed(ActionEvent e) { 
				try {
					load();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (ClassNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} 
		});
		
		add(jpBase);
		jpBase.setLayout(new GridLayout(4,1));
		jpBase.add(jpTop);
		jpBase.add(jpActions);
		jpBase.add(jpList);
		jpBase.add(jpBottom);
		
		jpTop.setLayout(new GridLayout(4,2));
		jpTop.add(jl0);
		jpTop.add(jtf0);
		jpTop.add(jl1);
		jpTop.add(jtf1);
		jpTop.add(jl2);
		jpTop.add(jtf2);
		jpTop.add(jl3);
		jpTop.add(jtf3);
		
		jpActions.setLayout(new GridLayout(1,6));
		jpActions.add(jbCreate);
		jpActions.add(jbNext);
		jpActions.add(jbPrevious);
		jpActions.add(jbList);
		jpActions.add(jbSave);
		jpActions.add(jbLoad);
		
		jtaData = new JTextArea();
		
		jpActions.setLayout(new GridLayout(1,1));
		jpList.add(jtaData);
		
		data = new GData[0];
		
		jdError = new JDialog(this, "Error");
		
		jlError = new JLabel();
		
		jdError.add(jlError);
		
		jdError.setSize(400,200);
		
		setVisible(true);
	}
	
	public static void main(String[] args) {
		GUI gui = new GUI();
	}
	
	public void create() {
		
		System.out.printf("%s",jl0.getText());
		
		if(jtf0.getText().equals("")) {
			jlError.setText("Name must not be null or lenth eqauls to 0");
			jdError.setVisible(true);
		}

		else if(!jtf1.getText().matches("[0-9]+") || !jtf2.getText().matches("[0-9]+") || !jtf3.getText().matches("[0-9]+")) {
			jlError.setText("Please enter a numerique values for red, green and blue fields.");
			jdError.setVisible(true);
		}
		
		else if(Integer.parseInt(jtf1.getText()) < 0 || Integer.parseInt(jtf1.getText())  > 255 || Integer.parseInt(jtf2.getText())  < 0 || Integer.parseInt(jtf2.getText())  > 255 || Integer.parseInt(jtf3.getText())  < 0 || Integer.parseInt(jtf3.getText())  > 255) {
			jlError.setText("Please enter valid values between 0 and 255.");
			jdError.setVisible(true);
		}
		
		else {
			
			ArrayList<GData> tempList = new ArrayList<GData>(Arrays.asList(data));
			
			GData gd = new GData(jtf0.getText(),Integer.parseInt(jtf1.getText()),Integer.parseInt(jtf2.getText()),Integer.parseInt(jtf3.getText()));
			
			this.index++;
			
			tempList.add(gd);
			
			data = new GData[tempList.size()];
	        
	        for(int i = 0; i < tempList.size(); i++) {
	        	data[i] = tempList.get(i);
	        }
			
			jpBottom.setBackground(new Color(gd.getR(), gd.getG(), gd.getB()));
		
		}
		
	}
	
	public void next() {
		if( index < data.length - 1) {
			this.index++;
			
			jpBottom.setBackground(new Color(data[index].getR(), data[index].getG(), data[index].getB()));
		}
	}
	
	public void previous() {
		if(index > 0) {
			this.index--;
			
			jpBottom.setBackground(new Color(data[index].getR(), data[index].getG(), data[index].getB()));
		}
	}
	
	public void list() {
		
		String temp;
		
		for(int i = 0; i < data.length; i++) {
			
			temp = "{ Name: " + data[i].getName() + ", Red: " + data[i].getR() + ", Green " + data[i].getG() + ", Blue: " + data[i].getB() + " }\n" ;
			
			jtaData.append(temp);
		}
		
	}
	
	public void save() throws IOException {
		
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream("colors.dat"));
				
        output.writeObject(data);
        
        output.close();
	}
	
	public void load() throws IOException, ClassNotFoundException {
		
		ObjectInputStream input = new ObjectInputStream(new FileInputStream("colors.dat"));
        
		data = (GData[]) input.readObject();
		
        index = - 1;
        
        jpBottom.setBackground(new Color(238,238,238));
        
        input.close();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}

class GData implements Serializable {
	private String name;
	private int r, g, b;
	
	public GData(String name, int r, int g, int b) {
		setName(name);
		setR(r);
		setG(g);
		setB(b);
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		if(name != null && name.length()>0) {
			this.name = name;
		} else {
			return;
		}
	}
	
	public int getR() {
		return this.r;
	}
	
	public void setR(int r) {
		if(r >= 0 && r <= 255) {
			this.r = r;
		} else {
			return;
		}
	}
	
	public int getG() {
		return this.g;
	}
	
	public void setG(int g) {
		if(g >= 0 && g <= 255) {
			this.g = g;
		} else {
			return;
		}
	}
	
	public int getB() {
		return this.b;
	}
	
	public void setB(int b) {
		if(b >= 0 && b <= 255) {
			this.b = b;
		} else {
			return;
		}
	}
}
