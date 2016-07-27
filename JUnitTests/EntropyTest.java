import Main.DataSet;
import Main.Entropy;
import Main.ImportData;
import junit.framework.TestCase;


public class EntropyTest extends TestCase {

    DataSet data;
    public static final double delta = .001;

    public void setUp() throws Exception {
        super.setUp();
        data = ImportData.importData("C:\\Users\\EliFo\\OneDrive - Lakeside School\\Projects\\MachineLearningProject\\Random-Forest-Classification\\src\\Main\\data.csv");

    }

    //making sure that our methods correctly calculate entropy of a data set
    public void testEntropy() throws Exception {
        double e = Entropy.entropy(data.getData());
        assertEquals(0, e, delta);
    }

    //making sure we can calculate the entropy of a data set with respect to a specific attribute
    public void testAttributeEntropy() throws Exception {
        //test for the first attribute of the current data set
        double e = Entropy.attributeEntropy(data.getData(), data.getAttributes().get(1));
        assertEquals(.693, e,  delta);
    }

}
