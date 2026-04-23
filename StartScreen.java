import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Καθαρή, μίνιμαλ αρχική οθόνη με μαύρο φόντο.
 */
public class StartScreen extends World
{
    public StartScreen()
    {    
        super(800, 600, 1);
        
        // 1. Δημιουργία ενός κενού καμβά 800x600
        GreenfootImage bg = new GreenfootImage(800, 600);
        
        // 2. Βάφουμε όλο τον καμβά μαύρο
        bg.setColor(Color.BLACK);
        bg.fillRect(0, 0, 800, 600);
        
        // 3. Σχεδιασμός Τίτλου (Μεγάλα Κίτρινα Γράμματα)
        // Font(όνομα, bold, italic, μέγεθος)
        bg.setFont(new Font("Arial", true, false, 48)); 
        bg.setColor(Color.YELLOW);
        bg.drawString("GEM HUNTER", 230, 150);
        
        // 4. Σχεδιασμός Οδηγιών (Λευκά Γράμματα)
        bg.setFont(new Font("Arial", false, false, 24)); 
        bg.setColor(Color.WHITE);
        bg.drawString("Instructions:", 330, 250);
        
        bg.drawString("There are 3 levels. Collect all the gems", 190, 300);
        bg.drawString("to move to the next level before the time ends.", 150, 340);
        bg.drawString("Move with Left/Right arrows and jump with Up.", 150, 390);
        
        // 5. Σχεδιασμός Προτροπής (Έντονα Πράσινα Γράμματα στο κάτω μέρος)
        bg.setFont(new Font("Arial", true, false, 28)); 
        bg.setColor(Color.GREEN);
        bg.drawString("Press 'ENTER' to start...", 250, 500);
        
        // 6. Εφαρμογή της εικόνας που μόλις φτιάξαμε ως background του World
        setBackground(bg);
    }
    
    public void act()
    {
        // Περιμένουμε το Enter για να ξεκινήσει το παιχνίδι
        String key = Greenfoot.getKey();
        if ("enter".equals(key))
        {
            Greenfoot.setWorld(new Level(1, 0, 5)); 
        }
    }
}
