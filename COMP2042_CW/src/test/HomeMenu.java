/*
 *  Brick Destroy - A simple Arcade video game
 *   Copyright (C) 2017  Filippo Ranza
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package test;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


public class HomeMenu extends JComponent implements MouseListener, MouseMotionListener {

    private static final String GREETINGS = "Welcome to:";
    private static final String GAME_TITLE = "Brick Destroy";
    private static final String CREDITS = "Version 0.1";
    private static final String START_TEXT = "Start";
    private static final String MENU_TEXT = "Exit";

    /* player name */
    public static String pnames = "";

    private static final Color BG_COLOR = Color.GREEN.darker();
    private static final Color BORDER_COLOR = new Color(200,8,21); //Venetian Red
    private static final Color DASH_BORDER_COLOR = new  Color(255, 216, 0);//school bus yellow
    private static final Color TEXT_COLOR = new Color(16, 52, 166);//egyptian blue
    private static final Color CLICKED_BUTTON_COLOR = BG_COLOR.brighter();
    private static final Color CLICKED_TEXT = Color.WHITE;
    private static final int BORDER_SIZE = 5;
    private static final float[] DASHES = {12,6};

    private final Rectangle menuFace;
    private final Rectangle startButton;
    private final Rectangle PlayerName;
    private final Rectangle menuButton;

    private final BasicStroke borderStoke;
    private final BasicStroke borderStoke_noDashes;

    private final Font greetingsFont;
    private final Font gameTitleFont;
    private final Font creditsFont;
    private final Font buttonFont;

    private final GameFrame owner;

    private boolean startClicked;
    private boolean menuClicked;

    private boolean playerClicked;
    //SaveScore sv=new SaveScore();;
    PlayerName playerNam= new PlayerName();;

    public HomeMenu(GameFrame owner,Dimension area){

        this.setFocusable(true);
        this.requestFocusInWindow();

        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        this.owner = owner;



        menuFace = new Rectangle(new Point(0,0),area);
        this.setPreferredSize(area);

        //this.drawBackground();
        Dimension btnDim = new Dimension(area.width / 3, area.height / 12);
        startButton = new Rectangle(btnDim);
        menuButton = new Rectangle(btnDim);
        PlayerName = new Rectangle(btnDim);

        borderStoke = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND,0,DASHES,0);
        borderStoke_noDashes = new BasicStroke(BORDER_SIZE,BasicStroke.CAP_ROUND,BasicStroke.JOIN_ROUND);

        greetingsFont = new Font("Noto Mono",Font.PLAIN,25);
        gameTitleFont = new Font("Noto Mono",Font.BOLD,40);
        creditsFont = new Font("Monospaced",Font.PLAIN,10);
        buttonFont = new Font("Monospaced",Font.PLAIN,startButton.height-2);


//        PlayerName  pn = new PlayerName(10);
//        menuFace.add(pn,);
//        this.add(pn,BorderLayout.NORTH);
//        PlayerName  pn = new PlayerName(10);
//        pn.setLocation(0,0);
//        this.add(pn,BorderLayout.NORTH);




    }


    public void paint(Graphics g){
        drawMenu((Graphics2D)g);
    }


    public void drawMenu(Graphics2D g2d){

        drawContainer(g2d);

        /*
        all the following method calls need a relative
        painting directly into the HomeMenu rectangle,
        so the translation is made here so the other methods do not do that.
         */
        Color prevColor = g2d.getColor();
        Font prevFont = g2d.getFont();

        double x = menuFace.getX();
        double y = menuFace.getY();

        g2d.translate(x,y);

        //methods calls
        drawText(g2d);
        drawButton(g2d);

        //end of methods calls

        g2d.translate(-x,-y);
        g2d.setFont(prevFont);
        g2d.setColor(prevColor);

       playerNam.setVisible(true);
        //this.setLayout(new BorderLayout());
        //pn.setLocation(PlayerName.x, PlayerName.y);

        //g2d.(new PlayerName());
        //new PlayerName();

    }
private BufferedImage drawBackground()
{
//    BufferedImage img = new BufferedImage(menuFace.width, menuFace.height, BufferedImage.TYPE_INT_ARGB);
//
//    g2d = img.createGraphics();
//    TexturePaint tp = new TexturePaint(MenuBackground.BackgroundPanel(),menuFace);
//    g2d.drawImage(MenuBackground.BackgroundPanel(), 0, 0, null);
//    g2d.setPaint(tp);
    BufferedImage img = null;
    try {
        img = ImageIO.read(new File("Source/bg.jpg"));
    } catch (IOException e) {
        e.printStackTrace();
    }
    int w = img.getWidth(null);
    int h = img.getHeight(null);
    //MenuBackground.BackgroundPanel();
    return img;
}

    /**
     * Modify the drawContainer for the background.
     * @param g2d - g2D
     */
    private void drawContainer(Graphics2D g2d){
        Color prev = g2d.getColor();
//      g2d.drawImage(MenuBackground.BackgroundPanel(),menuFace,);
//      g2d.setColor(BG_COLOR);
//      g2d.fill(menuFace);
        int x=menuFace.x;
        int y=menuFace.y;
        g2d.drawImage(drawBackground(),x,y,null);
        g2d.draw(menuFace);
        Stroke tmp = g2d.getStroke();

        g2d.setStroke(borderStoke_noDashes);
        g2d.setColor(DASH_BORDER_COLOR);
        g2d.draw(menuFace);
        JTextField d =new JTextField();
        d.setSize(12,21);
        d.setLocation(20,20);
        this.add(d);
        g2d.setStroke(borderStoke);
        g2d.setColor(BORDER_COLOR);
        g2d.draw(menuFace);

        g2d.setStroke(tmp);
        g2d.setColor(prev);



    }

    private void drawText(Graphics2D g2d){

        g2d.setColor(TEXT_COLOR);

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D greetingsRect = greetingsFont.getStringBounds(GREETINGS,frc);
        Rectangle2D gameTitleRect = gameTitleFont.getStringBounds(GAME_TITLE,frc);
        Rectangle2D creditsRect = creditsFont.getStringBounds(CREDITS,frc);

        int sX,sY;

        sX = (int)(menuFace.getWidth() - greetingsRect.getWidth()) / 2;
        sY = (int)(menuFace.getHeight() / 4);

        g2d.setFont(greetingsFont);
        g2d.drawString(GREETINGS,sX,sY);

        sX = (int)(menuFace.getWidth() - gameTitleRect.getWidth()) / 2;
        sY += (int) gameTitleRect.getHeight() * 1.1;//add 10% of String height between the two strings

        g2d.setFont(gameTitleFont);
        g2d.drawString(GAME_TITLE,sX,sY);


        sX = (int)(menuFace.getWidth() - creditsRect.getWidth()) / 2;
        sY += (int) creditsRect.getHeight() * 1.1;

        g2d.setFont(creditsFont);
        g2d.drawString(CREDITS,sX,sY);


    }

    private void drawButton(Graphics2D g2d){

        FontRenderContext frc = g2d.getFontRenderContext();

        Rectangle2D txtRect = buttonFont.getStringBounds(START_TEXT,frc);
        Rectangle2D mTxtRect = buttonFont.getStringBounds(MENU_TEXT,frc);
        Rectangle2D pTxtRect = buttonFont.getStringBounds("Player :",frc);

        g2d.setFont(buttonFont);

        int x = (menuFace.width - startButton.width) / 2;
        int y =(int) ((menuFace.height - startButton.height) * 0.8);
        startButton.setLocation(x,y);

        x = (int)(startButton.getWidth() - txtRect.getWidth()) / 2;
        y = (int)(startButton.getHeight() - txtRect.getHeight()) / 2;

        x += startButton.x;
        y += startButton.y + (startButton.height * 0.9);




        if(startClicked){
            Color tmp = g2d.getColor();
            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(startButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(START_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(startButton);
            g2d.drawString(START_TEXT,x,y);
        }

        x = startButton.x;
        y = startButton.y;

        y *= 1.2;

        menuButton.setLocation(x,y);




        x = (int)(menuButton.getWidth() - mTxtRect.getWidth()) / 2;
        y = (int)(menuButton.getHeight() - mTxtRect.getHeight()) / 2;

        x += menuButton.x;
        y += menuButton.y + (startButton.height * 0.9);

        if(menuClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(menuButton);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString(MENU_TEXT,x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(menuButton);
            g2d.drawString(MENU_TEXT,x,y);
        }



        x = menuButton.x;
        y = startButton.y;
        x -= 20;
        y -= 120;
        PlayerName.setLocation(x,y);
        x = (int)(PlayerName.getWidth() - pTxtRect.getWidth()) / 2;
        y = (int)(PlayerName.getHeight() - pTxtRect.getHeight()) / 2;

        x += PlayerName.x;
        y += PlayerName.y;
        PlayerName.setSize(260,107);
        if(playerClicked){
            Color tmp = g2d.getColor();

            g2d.setColor(CLICKED_BUTTON_COLOR);
            g2d.draw(PlayerName);
            g2d.setColor(CLICKED_TEXT);
            g2d.drawString("Player :",x,y);
            g2d.setColor(tmp);
        }
        else{
            g2d.draw(PlayerName);
            g2d.setBackground(BG_COLOR);
            g2d.drawString("Player :",x,y);
        }
    }
//    private void drawChangePlayer(Graphics2D g2d){
//
//        FontRenderContext frc = g2d.getFontRenderContext();
//
//        Rectangle2D txtRect = buttonFont.getStringBounds("Player 1",frc);
//        //Rectangle2D mTxtRect = buttonFont.getStringBounds(MENU_TEXT,frc);
//
//        g2d.setFont(buttonFont);
//
//        int x = (menuFace.width - startButton.width) * 2;
//        int y =(int) ((menuFace.height - startButton.height) * 0.8);
//
//        startButton.setLocation(x,y);
//
//        x = (int)(startButton.getWidth() - txtRect.getWidth()) / 2;
//        y = (int)(startButton.getHeight() - txtRect.getHeight()) / 2;
//
//        x += startButton.x;
//        y += startButton.y + (startButton.height * 0.9);
//
//
//
//
//        if(startClicked){
//            Color tmp = g2d.getColor();
//            g2d.setColor(CLICKED_BUTTON_COLOR);
//            g2d.draw(startButton);
//            g2d.setColor(CLICKED_TEXT);
//            g2d.drawString(START_TEXT,x,y);
//            g2d.setColor(tmp);
//        }
//        else{
//            g2d.draw(startButton);
//            g2d.drawString(START_TEXT,x,y);
//        }
//
//        x = startButton.x;
//        y = startButton.y;
//
//        y *= 1.2;
//
//        menuButton.setLocation(x,y);
//
//
//
//
//        x = (int)(menuButton.getWidth() - mTxtRect.getWidth()) / 2;
//        y = (int)(menuButton.getHeight() - mTxtRect.getHeight()) / 2;
//
//        x += menuButton.x;
//        y += menuButton.y + (startButton.height * 0.9);
//
//        if(menuClicked){
//            Color tmp = g2d.getColor();
//
//            g2d.setColor(CLICKED_BUTTON_COLOR);
//            g2d.draw(menuButton);
//            g2d.setColor(CLICKED_TEXT);
//            g2d.drawString(MENU_TEXT,x,y);
//            g2d.setColor(tmp);
//        }
//        else{
//            g2d.draw(menuButton);
//            g2d.drawString(MENU_TEXT,x,y);
//        }
//
//    }
    /**
     * This method let the text field invisible.
     * @param mouseEvent - respond to mouse pressed.
     */
    @Override
    public void mouseClicked(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p)){
            String pname = playerNam.pname;
            pnames=pname;
            //sv.SaveScorename(pname);
           playerNam.setVisible(false);
           playerNam.dispose();
          // playerNam=null;

           owner.enableGameBoard();

        }
        else if(menuButton.contains(p)){
            System.out.println("Goodbye " + System.getProperty("user.name"));
            System.exit(0);
        }
        else if(PlayerName.contains(p)){

            //System.out.println("Goodbye " + System.getProperty("user.name"));
            //System.exit(0);
        }


    }

    @Override
    public void mousePressed(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p)){
            startClicked = true;
            repaint(startButton.x,startButton.y,startButton.width+1,startButton.height+1);

        }
        else if(menuButton.contains(p)){
            menuClicked = true;
            repaint(menuButton.x,menuButton.y,menuButton.width+1,menuButton.height+1);
        }
//        else if(PlayerName.contains(p)){
//            playerClicked= true;
//            repaint(PlayerName.x,PlayerName.y,PlayerName.width+1,PlayerName.height+1);
//        }
    }

    @Override
    public void mouseReleased(MouseEvent mouseEvent) {
        if(startClicked ){
            startClicked = false;
            repaint(startButton.x,startButton.y,startButton.width+1,startButton.height+1);
        }
        else if(menuClicked){
            menuClicked = false;
            repaint(menuButton.x,menuButton.y,menuButton.width+1,menuButton.height+1);
        }
//        else if(playerClicked){
//            playerClicked = false;
//            repaint(PlayerName.x,PlayerName.y,PlayerName.width+1,PlayerName.height+1);
//        }
    }

    @Override
    public void mouseEntered(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseExited(MouseEvent mouseEvent) {

    }


    @Override
    public void mouseDragged(MouseEvent mouseEvent) {

    }

    @Override
    public void mouseMoved(MouseEvent mouseEvent) {
        Point p = mouseEvent.getPoint();
        if(startButton.contains(p) || menuButton.contains(p) )
            this.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        else
            this.setCursor(Cursor.getDefaultCursor());

    }
}
