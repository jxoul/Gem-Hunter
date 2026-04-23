import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Καθαρή, μίνιμαλ οθόνη Νίκης με μαύρο φόντο.
 */
public class VictoryScreen extends World
{
    public VictoryScreen(int finalScore)
    {    
        super(800, 600, 1); 
        
        // 1. Δημιουργία κενού καμβά 800x600
        GreenfootImage bg = new GreenfootImage(800, 600);
        
        // 2. Βάφουμε όλο τον καμβά μαύρο
        bg.setColor(Color.BLACK);
        bg.fillRect(0, 0, 800, 600);
        
        // 3. Σχεδιασμός Τίτλου (Μεγάλα Κυανά Γράμματα)
        bg.setFont(new Font("Arial", true, false, 54)); 
        bg.setColor(Color.CYAN);
        bg.drawString("LEVEL COMPLETE!", 160, 250);
        
        // 4. Εμφάνιση του τελικού Σκορ (Λευκά Γράμματα)
        bg.setFont(new Font("Arial", false, false, 36)); 
        bg.setColor(Color.WHITE);
        bg.drawString("Score: " + finalScore, 320, 350);
        
        // 5. Σχεδιασμός Προτροπής (Πράσινα Γράμματα)
        bg.setFont(new Font("Arial", true, false, 28)); 
        bg.setColor(Color.GREEN);
        bg.drawString("Press 'ENTER' to return to menu...", 190, 450);
        
        // 6. Εφαρμογή της εικόνας ως background
        setBackground(bg);
    }
    
    public void act()
    {
        String key = Greenfoot.getKey();
        if ("enter".equals(key))
        {
            Greenfoot.setWorld(new StartScreen()); 
        }
    }
}