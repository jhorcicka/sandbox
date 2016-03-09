package cz.hk.gmc.mocking;

import org.easymock.EasyMock;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.easymock.PowerMock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Service.class)
public class ServiceTestMockito {
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