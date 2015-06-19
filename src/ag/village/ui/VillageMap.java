package ag.village.ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JPanel;

import ag.village.House;

public class VillageMap extends JFrame {
	public static final int MAP_WIDTH_PIXELS = 800;
	public static final int MAP_HEIGHT_PIXELS = 600;
	public static final int TILE_SIZE = 25;
	public static final int MAP_WIDTH = MAP_WIDTH_PIXELS / TILE_SIZE;
	public static final int MAP_HEIGHT = MAP_HEIGHT_PIXELS / TILE_SIZE;
	
	public static final int N_HOUSES = 30;
	
	Set<House> houses;
	
    JPanel drawPanel;
    

    public VillageMap() {
        super("Village Map");
        setSize(MAP_WIDTH_PIXELS, MAP_HEIGHT_PIXELS);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initHouses();
        
        drawPanel = new DrawPanel();
        add(drawPanel);
    }

    private void initHouses() {
    	houses = new HashSet<House>();
    	
    	for (int i=0; i < N_HOUSES; i++) {
    		
    		while (true) {
	    		int x = (int)Math.floor(Math.random() * (MAP_WIDTH - 4)) + 1;
	    		int y = (int)Math.floor(Math.random() * (MAP_HEIGHT - 4)) + 1;
	    		int w = (int)Math.floor(Math.random() * 3) + 1;
	    		int h = (int)Math.floor(Math.random() * 3) + 1;
	    		House newHouse = new House(x, y, w, h);
	    		
	    		boolean validPos = checkHousePos(newHouse);
	    		if (validPos) {
	    			houses.add(newHouse);
	    			break;
	    		}
    		}
    		
    	}
    }
    
    private boolean checkHousePos(House newHouse) {
    	for (House currHouse: houses) {
    		boolean xColl = (newHouse.getX() >= currHouse.getX() && newHouse.getX() <= currHouse.getX() + currHouse.getW())
    				|| (currHouse.getX() >= newHouse.getX() && currHouse.getX() <= newHouse.getX() + newHouse.getW());
    		boolean yColl = (newHouse.getY() >= currHouse.getY() && newHouse.getY() <= currHouse.getY() + currHouse.getH())
    				|| (currHouse.getY() >= newHouse.getY() && currHouse.getY() <= newHouse.getY() + newHouse.getH());
    		if (xColl && yColl)
    			return false;
    		
//    		if ( (Math.abs(newHouse.getX() - currHouse.getX()) * 2 < (newHouse.getW() + currHouse.getW())) &&
//    			(Math.abs(newHouse.getY() - currHouse.getY()) * 2 < (newHouse.getH() + currHouse.getH())) )
//    			return false;
    	}
    	
    	return true;
    }
    
    
    class DrawPanel extends JPanel {
        public DrawPanel() {
            setPreferredSize(new Dimension(MAP_WIDTH_PIXELS, MAP_HEIGHT_PIXELS));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            
            for (House house: houses) {
            	this.drawHouse(g, house);
            }
        }
        
        private void drawHouse(Graphics g, House house) {
        	int x = house.getX() * TILE_SIZE;
        	int y = house.getY() * TILE_SIZE;
        	int w = house.getW() * TILE_SIZE;
        	int h = house.getH() * TILE_SIZE;
            
        	g.drawRect(x, y, w, h);
        	g.drawLine(x, y, x + w, y + h);
        	g.drawLine(x + w, y, x , y + h);
        }
    }

    
	public static void main(String[] args) {
		VillageMap vm = new VillageMap();
        vm.setVisible(true);
	}

}
