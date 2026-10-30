
import java.util.ArrayList;

abstract class Animals {

    private String commonName;
    private String speciesName;      
    private double typicalSizeCm;    
    private double typicalWeightKg;  
    private String dietType;         
    private int lifespanYears;
    private String habitat;

  
    public Animals(String commonName, String speciesName, double typicalSizeCm,
                   double typicalWeightKg, String dietType, int lifespanYears, String habitat) {
        this.commonName = commonName;
        this.speciesName = speciesName;
        setTypicalSizeCm(typicalSizeCm);
        setTypicalWeightKg(typicalWeightKg);
        this.dietType = dietType;
        this.lifespanYears = lifespanYears;
        this.habitat = habitat;
    }

   
    public abstract String getCategory();     
    public abstract String move();            
    public abstract String specificFeatures(); 

    public boolean isPredator() {
        return dietType.equalsIgnoreCase("Predator");
    }
    public void describe() {
        System.out.println("==========================================");
        System.out.println(commonName + " (" + getCategory() + ")");
        System.out.println("==========================================");
        System.out.println("Species name   : " + speciesName);
        System.out.println("Typical size   : " + typicalSizeCm + " cm");
        System.out.println("Typical weight : " + typicalWeightKg + " kg");
        System.out.println("Diet           : " + dietType);
        System.out.println("Lifespan       : " + lifespanYears + " years");
        System.out.println("Habitat        : " + habitat);
        System.out.println("Movement       : " + move());
        System.out.println(specificFeatures());   // polymorphic call
        System.out.println();
    }

    public String getCommonName() { return commonName; }
    public void setCommonName(String commonName) { this.commonName = commonName; }

    public String getSpeciesName() { return speciesName; }
    public void setSpeciesName(String speciesName) { this.speciesName = speciesName; }

    public double getTypicalSizeCm() { return typicalSizeCm; }
    public void setTypicalSizeCm(double typicalSizeCm) {
        if (typicalSizeCm <= 0) {
            throw new IllegalArgumentException("Size must be positive.");
        }
        this.typicalSizeCm = typicalSizeCm;
    }

    public double getTypicalWeightKg() { return typicalWeightKg; }
    public void setTypicalWeightKg(double typicalWeightKg) {
        if (typicalWeightKg <= 0) {
            throw new IllegalArgumentException("Weight must be positive.");
        }
        this.typicalWeightKg = typicalWeightKg;
    }

    public String getDietType() { return dietType; }
    public void setDietType(String dietType) { this.dietType = dietType; }

    public int getLifespanYears() { return lifespanYears; }
    public void setLifespanYears(int lifespanYears) { this.lifespanYears = lifespanYears; }

    public String getHabitat() { return habitat; }
    public void setHabitat(String habitat) { this.habitat = habitat; }

    @Override
    public String toString() {
        return commonName + " [" + getCategory() + ", " + dietType + "]";
    }
}

class Bird extends Animals {

    private double wingspanCm;
    private boolean canFly;
    private String beakType;
    private boolean migratory;

    public Bird(String commonName, String speciesName, double typicalSizeCm,
                double typicalWeightKg, String dietType, int lifespanYears, String habitat,
                double wingspanCm, boolean canFly, String beakType, boolean migratory) {
        super(commonName, speciesName, typicalSizeCm, typicalWeightKg,
              dietType, lifespanYears, habitat);   // call the Animals constructor
        this.wingspanCm = wingspanCm;
        this.canFly = canFly;
        this.beakType = beakType;
        this.migratory = migratory;
    }

    @Override
    public String getCategory() {
        return "Bird";
    }

    @Override
    public String move() {
        return canFly ? "Flies using its wings" : "Walks or runs (flightless bird)";
    }

    @Override
    public String specificFeatures() {
        return "-- Bird features --\n"
             + "Number of legs : 2\n"
             + "Wingspan       : " + wingspanCm + " cm\n"
             + "Can fly        : " + (canFly ? "Yes" : "No") + "\n"
             + "Beak type      : " + beakType + "\n"
             + "Migratory      : " + (migratory ? "Yes" : "No");
    }

    public double getWingspanCm() { return wingspanCm; }
    public void setWingspanCm(double wingspanCm) { this.wingspanCm = wingspanCm; }

    public boolean getCanFly() { return canFly; }
    public void setCanFly(boolean canFly) { this.canFly = canFly; }

    public String getBeakType() { return beakType; }
    public void setBeakType(String beakType) { this.beakType = beakType; }

    public boolean isMigratory() { return migratory; }
    public void setMigratory(boolean migratory) { this.migratory = migratory; }
}

class LandAnimal extends Animals {

    private int numberOfLegs;
    private double topSpeedKmh;
    private boolean hasFur;
    private boolean isMammal;

    public LandAnimal(String commonName, String speciesName, double typicalSizeCm,
                      double typicalWeightKg, String dietType, int lifespanYears, String habitat,
                      int numberOfLegs, double topSpeedKmh, boolean hasFur, boolean isMammal) {
        super(commonName, speciesName, typicalSizeCm, typicalWeightKg,
              dietType, lifespanYears, habitat);
        this.numberOfLegs = numberOfLegs;
        this.topSpeedKmh = topSpeedKmh;
        this.hasFur = hasFur;
        this.isMammal = isMammal;
    }

    @Override
    public String getCategory() {
        return "Land Animal";
    }

    @Override
    public String move() {
        if (numberOfLegs == 0) {
            return "Slithers along the ground";
        }
        return "Walks/runs on " + numberOfLegs + " legs (up to " + topSpeedKmh + " km/h)";
    }

    @Override
    public String specificFeatures() {
        return "-- Land animal features --\n"
             + "Number of legs : " + numberOfLegs + "\n"
             + "Top speed      : " + topSpeedKmh + " km/h\n"
             + "Has fur        : " + (hasFur ? "Yes" : "No") + "\n"
             + "Mammal         : " + (isMammal ? "Yes" : "No");
    }

    public int getNumberOfLegs() { return numberOfLegs; }
    public void setNumberOfLegs(int numberOfLegs) { this.numberOfLegs = numberOfLegs; }

    public double getTopSpeedKmh() { return topSpeedKmh; }
    public void setTopSpeedKmh(double topSpeedKmh) { this.topSpeedKmh = topSpeedKmh; }

    public boolean getHasFur() { return hasFur; }
    public void setHasFur(boolean hasFur) { this.hasFur = hasFur; }

    public boolean getIsMammal() { return isMammal; }
    public void setIsMammal(boolean isMammal) { this.isMammal = isMammal; }
}
class Fish extends Animals {

    private int numberOfFins;
    private String waterType;     
    private double maxDepthM;     
    private boolean hasScales;

    public Fish(String commonName, String speciesName, double typicalSizeCm,
                double typicalWeightKg, String dietType, int lifespanYears, String habitat,
                int numberOfFins, String waterType, double maxDepthM, boolean hasScales) {
        super(commonName, speciesName, typicalSizeCm, typicalWeightKg,
              dietType, lifespanYears, habitat);
        this.numberOfFins = numberOfFins;
        this.waterType = waterType;
        this.maxDepthM = maxDepthM;
        this.hasScales = hasScales;
    }

    @Override
    public String getCategory() {
        return "Fish";
    }

    @Override
    public String move() {
        return "Swims using its " + numberOfFins + " fins";
    }

    @Override
    public String specificFeatures() {
        return "-- Fish features --\n"
             + "Number of fins : " + numberOfFins + "\n"
             + "Water type     : " + waterType + "\n"
             + "Max depth      : " + maxDepthM + " m\n"
             + "Has scales     : " + (hasScales ? "Yes" : "No");
    }

    public int getNumberOfFins() { return numberOfFins; }
    public void setNumberOfFins(int numberOfFins) { this.numberOfFins = numberOfFins; }

    public String getWaterType() { return waterType; }
    public void setWaterType(String waterType) { this.waterType = waterType; }

    public double getMaxDepthM() { return maxDepthM; }
    public void setMaxDepthM(double maxDepthM) { this.maxDepthM = maxDepthM; }

    public boolean getHasScales() { return hasScales; }
    public void setHasScales(boolean hasScales) { this.hasScales = hasScales; }
}

public class AnimalsApp {

    public static void main(String[] args) {

    
        ArrayList<Animals> zoo = new ArrayList<>();

        // Birds
        zoo.add(new Bird("Bald Eagle", "Haliaeetus leucocephalus", 90, 4.5,
                "Predator", 20, "Forests near lakes and rivers",
                200, true, "Hooked", false));
        zoo.add(new Bird("Ostrich", "Struthio camelus", 250, 120,
                "Omnivore", 40, "African savanna",
                200, false, "Flat and broad", false));

        // Land animals
        zoo.add(new LandAnimal("African Elephant", "Loxodonta africana", 330, 6000,
                "Vegetarian", 65, "Savanna and forest",
                4, 40, false, true));
        zoo.add(new LandAnimal("Cheetah", "Acinonyx jubatus", 130, 50,
                "Predator", 12, "Grasslands",
                4, 110, true, true));

        // Fish
        zoo.add(new Fish("Great White Shark", "Carcharodon carcharias", 450, 1100,
                "Predator", 70, "Coastal ocean waters",
                7, "Saltwater", 1200, true));
        zoo.add(new Fish("Goldfish", "Carassius auratus", 20, 0.3,
                "Omnivore", 10, "Ponds and aquariums",
                7, "Freshwater", 2, true));

        for (Animals a : zoo) {
            a.describe();
        }
        int birds = 0, land = 0, fish = 0, predators = 0;
        Animals heaviest = zoo.get(0);

        for (Animals a : zoo) {
            if (a instanceof Bird) {
                birds++;
            } else if (a instanceof LandAnimal) {
                land++;
            } else if (a instanceof Fish) {
                fish++;
            }

            if (a.isPredator()) {
                predators++;
            }
            if (a.getTypicalWeightKg() > heaviest.getTypicalWeightKg()) {
                heaviest = a;
            }
        }

        System.out.println("============== SUMMARY ==============");
        System.out.println("Total animals : " + zoo.size());
        System.out.println("Birds         : " + birds);
        System.out.println("Land animals  : " + land);
        System.out.println("Fish          : " + fish);
        System.out.println("Predators     : " + predators);
        System.out.println("Heaviest      : " + heaviest.getCommonName()
                + " (" + heaviest.getTypicalWeightKg() + " kg)");

        System.out.println("\nPredators in the list:");
        for (Animals a : zoo) {
            if (a.isPredator()) {
                System.out.println("  - " + a);
            }
        }

    
        System.out.println("\nWingspans of the birds:");
        for (Animals a : zoo) {
            if (a instanceof Bird) {
                Bird b = (Bird) a;
                System.out.println("  - " + b.getCommonName() + ": " + b.getWingspanCm() + " cm");
            }
        }

        try {
            zoo.get(0).setTypicalWeightKg(-5);
        } catch (IllegalArgumentException e) {
            System.out.println("\nError caught: " + e.getMessage());
        }
    }
}