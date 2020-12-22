package nl.hi.kuba.mocking;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.mockito.plugins.MockMaker;

import org.junit.Test;

public class ServiceTestMockito {
    @Test
    public void testFinal() {
        try {
            final Service mock = mock(Service.class);
            //final Service mock = MockMaker.createMock(Service.class);
            //final Service mock = mock(Service.class);
            //when(mock.finalMethod()).thenReturn("Mocked");
            given(mock.finalMethod()).willReturn("Mocked");

            System.out.println("finalMethod=" + mock.finalMethod());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}