import Main.ImportData;
import Main.Entropy;
import junit.framework.TestCase;
// Author: EliFo 
// Section C
// Assignment: 
// Description: 
// Version: 
// Class Name: 
// 7/26/2016.

public class EntropyTest extends TestCase {

    ImportData importer;

    public void setUp() throws Exception {
        super.setUp();
        importer = new ImportData("C:\\Users\\EliFo\\OneDrive - Lakeside School\\Projects\\MachineLearningProject\\Random-Forest-Classification\\src\\Main\\data.csv");

    }

    public void testEntropy() throws Exception {
        double e = Entropy.entropy(importer.getData());
        assertEquals(e, .94, .0001);
    }

    public void testAttributeEntropy() throws Exception {

    }

}
