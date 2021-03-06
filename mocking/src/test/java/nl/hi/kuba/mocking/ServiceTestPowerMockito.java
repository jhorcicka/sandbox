package nl.hi.kuba.mocking;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Service.class)
public class ServiceTestPowerMockito {
    @Test
    public void testBasePublicFinal() {
        try {
            Service2 mock = PowerMockito.mock(Service2.class);
            PowerMockito.when(mock.publicFinalMethodInAbstract()).thenReturn("Mocked");

            System.out.println("publicFinalMethodInAbstract=" + mock.publicFinalMethodInAbstract());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFinalWithArguments() {
        try {
            Service mock = PowerMockito.mock(Service.class);
            PowerMockito.when(mock.finalMethodWithArguments(42)).thenReturn("Mocked");

            System.out.println("finalMethodWithArguments=" + mock.finalMethodWithArguments(42));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testFinal() {
        try {
            Service mock = PowerMockito.mock(Service.class);
            PowerMockito.when(mock.finalMethod()).thenReturn("Mocked");

            System.out.println("finalMethod=" + mock.finalMethod());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testPublic() {
        try {
            Service mock = Mockito.mock(Service.class);
            Mockito.when(mock.publicMethod()).thenReturn("Mocked");

            System.out.println("publicMethod=" + mock.publicMethod());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}