import greenfoot.*;

public class Monster extends Actor
{
    // Movement Stats
    protected int speed;             
    protected int distanceMoved = 0;     
    protected int maxDistance;     

    // Animation Variables
    protected GreenfootImage[] walkRight;
    protected GreenfootImage[] walkLeft;
    protected int imageIndex = 0;
    protected int animationTimer = 0;
    protected int animationDelay = 6; 
    protected boolean facingRight = true;

    // Constructor: Takes the 'level' to load the correct images dynamically
    public Monster(int level, int platformWidth)
    {
        speed = 1 + level; 
        
        // --- ΛΥΣΗ 1: ΑΠΟ-ΣΥΓΧΡΟΝΙΣΜΟΣ (Randomization) ---
        
        // 1. Τυχαία απόσταση περιπολίας (κάθε τέρας θα έχει άλλο όριο, από 100 έως 200)
        maxDistance = (platformWidth / 2) - 10; 
        
        // 2. Το 50% των τεράτων θα ξεκινάνε περπατώντας προς τα αριστερά!
        if (Greenfoot.getRandomNumber(2) == 0) 
        {
            speed = -speed;
            facingRight = false;
        }
        
        // ------------------------------------------------
        
        walkRight = new GreenfootImage[3];
        walkLeft = new GreenfootImage[3];
        
        for (int i = 0; i < 2; i++) 
        {
            String fileName = "monster-" + (i + 1) + ".png";
            walkRight[i] = new GreenfootImage(fileName);
            walkLeft[i] = new GreenfootImage(fileName);
            walkLeft[i].mirrorHorizontally(); 
        }
        
        if (facingRight) setImage(walkRight[0]);
        else setImage(walkLeft[0]);
    }

    public void act()
    {
        patrol();
        animate(); 
    }
    
    protected void patrol()
    {
        setLocation(getX() + speed, getY());
        distanceMoved = distanceMoved + Math.abs(speed); 

        // --- ΛΥΣΗ 2: ΕΞΥΠΝΗ ΑΝΙΧΝΕΥΣΗ ΚΕΝΟΥ (Ledge Detection) ---
        // Χρησιμοποιούμε τη μέθοδο getOneObjectAtOffset για να κοιτάξουμε λίγο πιο ΜΠΡΟΣΤΑ (30 pixels) 
        // και λίγο πιο ΚΑΤΩ (30 pixels) από το τέρας, για να δούμε αν υπάρχει Πάτωμα (Ground).
        
        int xOffset = (speed > 0) ? 30 : -30; // Αν πάει δεξιά κοιτάει +30, αν πάει αριστερά κοιτάει -30
        int yOffset = 30; // Κοιτάει προς τα κάτω, στα πόδια του
        
        Actor groundAhead = getOneObjectAtOffset(xOffset, yOffset, Ground.class);

        // Το τέρας γυρνάει πίσω αν:
        // 1. Έφτασε τη μέγιστη απόσταση
        // 2. Χτύπησε στην άκρη της οθόνης
        // 3. Το groundAhead είναι null (ΔΗΛΑΔΗ ΒΡΗΚΕ ΚΕΝΟ!)
        if (distanceMoved >= maxDistance || isAtEdge() || groundAhead == null)
        {
            speed = -speed; 
            distanceMoved = 0; 
            
            if (speed > 0) facingRight = true;
            else facingRight = false;
        }
    }
    
    // The animation logic, identical to the Player's
    protected void animate()
    {
        animationTimer++;
        if (animationTimer % animationDelay == 0) 
        {
            imageIndex = (imageIndex + 1) % 2; // Loop between 0, 1, 2
            
            if (facingRight) 
            {
                setImage(walkRight[imageIndex]);
            } 
            else 
            {
                setImage(walkLeft[imageIndex]);
            }
        }
    }
}