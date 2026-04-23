import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Καθαρή, μίνιμαλ οθόνη Game Over με μαύρο φόντο.
 */
public class GameOverScreen extends World
{
    public GameOverScreen(int finalScore)
    {    
        super(800, 600, 1); 
        
        // 1. Δημιουργία κενού καμβά 800x600
        GreenfootImage bg = new GreenfootImage(800, 600);
        
        // 2. Βάφουμε όλο τον καμβά μαύρο
        bg.setColor(Color.BLACK);
        bg.fillRect(0, 0, 800, 600);
        
        // 3. Σχεδιασμός Τίτλου (Μεγάλα Κόκκινα Γράμματα)
        bg.setFont(new Font("Arial", true, false, 60)); 
        bg.setColor(Color.RED);
        bg.drawString("GAME OVER", 220, 250);
        
        // 4. Εμφάνιση του τελικού Σκορ (Λευκά Γράμματα)
        bg.setFont(new Font("Arial", false, false, 36)); 
        bg.setColor(Color.WHITE);
        bg.drawString("Final Score: " + finalScore, 280, 350);
        
        // 5. Σχεδιασμός Προτροπής (Πράσινα Γράμματα)
        bg.setFont(new Font("Arial", true, false, 28)); 
        bg.setColor(Color.GREEN);
        bg.drawString("Press 'ENTER' to play again...", 220, 450);
        
        // 6. Εφαρμογή της εικόνας ως background
        setBackground(bg);
    }
    
    public void act()
    {
        String key = Greenfoot.getKey();
        if ("enter".equals(key))
        {
            // Επιστροφή στην αρχική οθόνη για να παίξει ξανά
            Greenfoot.setWorld(new StartScreen()); 
        }
    }
}