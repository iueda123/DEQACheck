package iu.LCAC.tests;

import iu.LCAC.Mediator.action.ActionMediator;
import iu.LCAC.Member.MemberIntrfc;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ActionMediatorTest {

    @Test
    public void createActnMembersRegistersExpectedActions() {
        ActionMediator mediator = new ActionMediator();
        Map<String, MemberIntrfc> map = mediator.getMemberMap();

        assertEquals(8, map.size(), "Unexpected number of actions registered");
        assertTrue(map.containsKey("change_color_of_west"));
        assertTrue(map.containsKey("change_color_of_center"));
        assertTrue(map.containsKey("change_color_of_east"));
        assertTrue(map.containsKey("change_text"));
        assertTrue(map.containsKey("initialize_sample_text_field"));
        assertTrue(map.containsKey("load_accelerator_settings"));
        assertTrue(map.containsKey("save_accelerator_settings"));
        assertTrue(map.containsKey("set_acceleration_on_next_click"));
    }
}
