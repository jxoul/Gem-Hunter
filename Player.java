import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class Player here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Player extends Actor
{
    private int speed = 5;           // Horizontal movement speed
    private int vSpeed = 0;          // Vertical speed (falling/jumping)
    private int acceleration = 1;    // Gravity power
    private int jumpStrength = -20;  // Jump power (negative because Y goes up in Greenfoot)

    
    private GreenfootImage[] walkRight;
    private GreenfootImage[] walkLeft;
    private int imageIndex = 0;
    private int animationTimer = 0;
    private int animationDelay = 5;
    private boolean facingRight = true;
    private boolean isMoving = false;
    
    public Player()
    {
        walkRight = new GreenfootImage[3];
        walkLeft = new GreenfootImage[3];
        
        for (int i = 0; i < walkRight.length; i++) 
        {
            walkRight[i] = new GreenfootImage("character" + (i + 1) + ".png");
            walkLeft[i] = new GreenfootImage("character" + (i + 1) + ".png");
            walkLeft[i].mirrorHorizontally();
        }
        
        // Set the default starting image
        setImage(walkRight[0]); 
    }
    
   public void act()
    {
        moveHorizontally();      // 1. Ελέγχουμε την οριζόντια κίνηση ΠΡΩΤΑ
        applyGravityAndJump();   // 2. Μετά ελέγχουμε το άλμα και την πτώση
        animate(); 
        collectGems();
        checkEnemy();
        checkSpikes();
        
        // --- ΕΛΕΓΧΟΣ: Πτώση στο Χάσμα ---
        if (getY() > 590) 
        {
            Level world = (Level) getWorld();
            world.loseLife(); 
            
            // Επαναφορά στο σταθερό Safe Zone
            setLocation(50, 430); 
            vSpeed = 0;
        }
    }
    
    private void moveHorizontally()
    {
        isMoving = false;
        int dx = 0;
        
        if (Greenfoot.isKeyDown("right"))
        {
            dx = speed;
            facingRight = true;
            isMoving = true;
        }
        if (Greenfoot.isKeyDown("left"))
        {
            dx = -speed;
            facingRight = false;
            isMoving = true;
        }
        
        // Κάνουμε την κίνηση
        setLocation(getX() + dx, getY());
        
        // Αν πέσαμε σε ΤΟΙΧΟ (Ground), πηγαίνουμε αμέσως πίσω!
        if (isTouching(Ground.class))
        {
            setLocation(getX() - dx, getY()); 
        }
    }
    
    private void applyGravityAndJump()
    {
        // Ελέγχουμε αν υπάρχει πάτωμα ΑΚΡΙΒΩΣ κάτω από τα πόδια μας
        int playerBottom = getImage().getHeight() / 2;
        Actor groundBelow = getOneObjectAtOffset(0, playerBottom + 2, Ground.class);
        
        // Άλμα ΜΟΝΟ αν πατάμε έδαφος
        if (Greenfoot.isKeyDown("up") && groundBelow != null)
        {
            vSpeed = jumpStrength; 
        }
        
        // Πτώση
        vSpeed = vSpeed + acceleration; 
        setLocation(getX(), getY() + vSpeed);
        
        // Διόρθωση σύγκρουσης (Πάτωμα / Ταβάνι)
        if (isTouching(Ground.class)) 
        {
            if (vSpeed > 0) // Αν πέφτουμε προς τα κάτω
            {
                while (isTouching(Ground.class)) 
                {
                    setLocation(getX(), getY() - 1); // Σπρώξιμο πάνω
                }
                
                // ΔΙΑΓΡΑΨΑΜΕ ΤΗ ΓΡΑΜΜΗ: setLocation(getX(), getY() + 1);
                // Πλέον ο παίκτης πατάει ΤΕΛΕΙΑ πάνω στο τούβλο, χωρίς να το διαπερνάει!
                
                vSpeed = 0; 
            }
            else if (vSpeed < 0) // Αν πηδάμε προς τα πάνω (χτυπάμε ταβάνι)
            {
                while (isTouching(Ground.class)) 
                {
                    setLocation(getX(), getY() + 1); // Σπρώξιμο κάτω
                }
                vSpeed = 0; 
            }
        }
    }
    
    
    private void animate()
    {
        // If the player is standing still, show the idle frame and reset the timer
        if (!isMoving) 
        {
            if (facingRight) setImage(walkRight[0]);
            else setImage(walkLeft[0]);
            animationTimer = 0;
            return; // Exit the method early
        }
        
        // Increase the timer. Only change the image if the timer reaches the delay
        animationTimer++;
        if (animationTimer % animationDelay == 0) 
        {
            // Move to the next image, and loop back to 0 if we reach the end of the array
            imageIndex = (imageIndex + 1) % walkRight.length; 
            
            // Set the correct image based on the direction the player is facing
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
    
   private void collectGems()
    {
        Actor gem = getOneIntersectingObject(Gem.class);
        if (gem != null)
        {
            Greenfoot.playSound("gem.wav");
            getWorld().removeObject(gem);
            Level world = (Level) getWorld(); 
            world.addScore(10); 
        }
    }
    
   private void checkEnemy()
    {
        Actor enemy = getOneIntersectingObject(Monster.class);
        
        if (enemy != null)
        {
            Level world = (Level) getWorld();
            world.loseLife(); 
            
            // Επαναφορά στο σταθερό Safe Zone
            setLocation(50, 430); 
            vSpeed = 0; 
        }
    }
    
    private void checkSpikes()
    {
        // Αν πατήσουμε πάνω στα καρφιά (Spike)
        if (isTouching(Spike.class))
        {
            Level world = (Level) getWorld();
            world.loseLife(); 
            
            // Επαναφορά στο Safe Zone!
            setLocation(50, 430); 
            vSpeed = 0; 
        }
    }
}
