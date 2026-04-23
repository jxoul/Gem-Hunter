import greenfoot.*; 

public class Level extends World
{
    private int currentLevel;
    private int score; 
    private int lives; 
    private int time;     
    private int frames = 0;    

    private static GreenfootSound bgMusic = new GreenfootSound("cave.mp3");
    public Level(int levelNum, int currentScore, int currentLives)
    {    
        super(800, 600, 1); 
        
        this.currentLevel = levelNum;
        this.score = currentScore;
        this.lives = currentLives;
        
        setBackground("background.png");
        
        this.time = 70 - (levelNum * 10);
        
        prepare(); 
        updateHUD(); 
        if (!bgMusic.isPlaying()) 
        {
            bgMusic.playLoop(); 
        }
    }
    
    public void started()
    {
        bgMusic.playLoop(); 
    }
    
    public void stopped()
    {
        bgMusic.pause(); 
    }
    
    public void act()
    {
        countTime();
        checkWinOrLose();
    }
    
    private void countTime()
    {
        frames++;
        if (frames % 60 == 0)
        {
            time--;
            updateHUD();
        }
    }
    
    private void checkWinOrLose()
    {
        if (getObjects(Gem.class).isEmpty())
        {
            if (currentLevel >= 4) 
            {
                Greenfoot.playSound("win.wav");
                Greenfoot.setWorld(new VictoryScreen(score));
            }
            else 
            {
                Greenfoot.playSound("level.wav");
                Greenfoot.setWorld(new Level(currentLevel + 1, score, lives)); 
            }
        }
        
        if (time <= 0)
        {
            Greenfoot.playSound("lose.mp3");
            Greenfoot.setWorld(new GameOverScreen(score));
        }
    }
    
    
 private void prepare()
    {
        int bottomY = 560; 
        int b = 40; 
        
       
        addObject(new Ground(), b, bottomY - (b * 2));  
        addObject(new Ground(), b * 2, bottomY - (b * 2)); 
        addObject(new Ground(), b * 3, bottomY - (b * 2)); 
        
        addObject(new Player(), b * 2, bottomY - (b * 3) - 10); 
    

        if (currentLevel == 1)
        {
           
            for (int x = 240; x <= getWidth() + b; x = x + b) { addObject(new Ground(), x, bottomY); }
            
            int floor1Y = bottomY - 120;
            for (int j = 0; j < 5; j++) { addObject(new Ground(), 400 + (j * b), floor1Y); }
            
            addObject(new Gem(), 300, bottomY - b);
            addObject(new Gem(), 480, floor1Y - b);
            
            addObject(new Monster(currentLevel, 5 * b), 480, floor1Y - 40);

        }
        else if (currentLevel == 2)
        {
            

            int floor1Y = bottomY; // Ο 1ος όροφος είναι στο χαμηλότερο σημείο (Y=560)
            // Ορίζουμε το ύψος για τις νέες πλατφόρμες (200 pixels πιο πάνω από το πάτωμα)
            int floor2Y = bottomY - 150; 
            
            // ==================== 3ΟΣ ΟΡΟΦΟΣ ====================
        // ==================== 3ΟΣ ΟΡΟΦΟΣ ====================
            // Χωρίζουμε το ύψος σε δύο μεταβλητές:
            int floor3RightY = bottomY - 400; // Η δεξιά πλατφόρμα παραμένει στο μέγιστο ύψος
            int floor3LeftY = bottomY - 320;  // Η αριστερή πλατφόρμα κατεβαίνει (είναι 80 pixels πιο χαμηλά)
            
            // --- ΑΡΙΣΤΕΡΗ ΠΛΑΤΦΟΡΜΑ (Πιο Χαμηλά) ---
            for (int x = 120; x <= 320; x += b) { 
                addObject(new Ground(), x, floor3LeftY); 
            }
            addObject(new Gem(), 160, floor3LeftY - b); 
            addObject(new Spike(), 280, floor3LeftY - b); 
            
            
            // --- ΔΕΞΙΑ ΠΛΑΤΦΟΡΜΑ (Πιο Ψηλά) ---
            for (int x = 520; x <= 800; x += b) { 
                addObject(new Ground(), x, floor3RightY); 
            }
            addObject(new Monster(currentLevel, 4 * b), 560, floor3RightY - 40); 
            addObject(new Gem(), 760, floor3RightY - b);
            // ΑΡΙΣΤΕΡΟ ΚΟΚΚΙΝΟ ΟΡΘΟΓΩΝΙΟ (4 τούβλα)
            for (int x = 320; x <= 440; x += b) { 
                addObject(new Ground(), x, floor2Y); 
            }
            
            // ΔΕΞΙ ΚΟΚΚΙΝΟ ΟΡΘΟΓΩΝΙΟ (4 τούβλα)
            for (int x = 600; x <= 720; x += b) { 
                addObject(new Ground(), x, floor2Y); 
            }
            // ΚΟΚΚΙΝΟ: Το συνεχόμενο πάτωμα του 1ου ορόφου
            for (int x = 200; x <= 800; x += b) { 
                addObject(new Ground(), x, floor1Y); 
            }
            
            // ΠΟΡΤΟΚΑΛΙ: Το 1ο Τέρας (προς τα αριστερά)
            addObject(new Monster(currentLevel, 3 * b), 320, floor1Y - 40); 
            
            // ΚΙΤΡΙΝΟ: Τα Καρφιά (περίπου στη μέση)
            addObject(new Spike(), 480, floor1Y - b); 
            
            // ΠΟΡΤΟΚΑΛΙ: Το 2ο Τέρας (προς τα δεξιά)
            addObject(new Monster(currentLevel, 3 * b), 640, floor1Y - 40); 
            
            // ΜΠΛΕ: Το Πετράδι (τέρμα δεξιά)
            addObject(new Gem(), 760, floor1Y - b);                    
        }
        else if (currentLevel == 3)
        {            
            int floor1Y = bottomY - 120; 
            int floor2Y = bottomY - 240; 
            int floor3Y = bottomY - 360;


            for (int x = 240; x <= 400; x = x + b) { addObject(new Ground(), x, bottomY); } 

            for (int x = 480; x <= 640; x = x + b) { addObject(new Ground(), x, bottomY); } 
            addObject(new Spike(), 520, bottomY - b);
            addObject(new Spike(), 600, bottomY - b);
            
            for (int x = 720; x <= 840; x = x + b) { addObject(new Ground(), x, bottomY); } 
            addObject(new Gem(), 760, bottomY - b); 
            
            for (int j = 0; j < 2; j++) { addObject(new Ground(), 360 + (j * b), floor1Y + 40); }
            
            for (int j = 0; j < 2; j++) { addObject(new Ground(), 720 + (j * b), floor2Y); }
            addObject(new Gem(), 720, floor2Y - b);

            for (int j = 0; j < 2; j++) { addObject(new Ground(), 480 + (j * b), floor3Y + 40); }

            for (int j = 0; j < 4; j++) { addObject(new Ground(), 480 + (j * b), floor1Y); }
            addObject(new Monster(currentLevel, 4 * b), 520, floor1Y - 40);
            
            for (int j = 0; j < 5; j++) { addObject(new Ground(), 240 + (j * b), floor2Y); }
            addObject(new Monster(currentLevel, 5 * b), 320, floor2Y - 40);
            addObject(new Gem(), 240, floor2Y - b); 
            
            
            for (int j = 0; j < 3; j++) { addObject(new Ground(), 360 + (j * b), floor3Y); }
            addObject(new Gem(), 400, floor3Y - b);
        }
        
    }
    
    public void addScore(int points)
    {
        score = score + points;
        updateHUD();
    }
    
    public void loseLife()
    {
        lives--; 
        updateHUD();
        if (lives <= 0) {
            Greenfoot.playSound("lose.mp3");
            Greenfoot.setWorld(new GameOverScreen(score));   
        }
        else{
            Greenfoot.playSound("life.wav");
        }
    }
    
    private void updateHUD()
    {
        showText("", getWidth() / 2, 30); 
        showText("Level: " + currentLevel + " | Score: " + score + " | Lives: " + lives + " | Time: " + time, getWidth() / 2, 30);
    }
}