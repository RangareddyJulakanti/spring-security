package org.springframework.security.vote;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.security.ConfigAttributeDefinition;
import org.springframework.security.providers.TestingAuthenticationToken;
import org.springframework.security.userdetails.hierarchicalroles.RoleHierarchyImpl;

public class RoleHierarchyVoterTests {

	@Test
	public void hierarchicalRoleIsIncludedInDecision() {
        RoleHierarchyImpl roleHierarchyImpl = new RoleHierarchyImpl();
        roleHierarchyImpl.setHierarchy("ROLE_A > ROLE_B");

        // User has role A, role B is required
        TestingAuthenticationToken auth = new TestingAuthenticationToken("user", "password", "ROLE_A");
        RoleHierarchyVoter voter = new RoleHierarchyVoter(roleHierarchyImpl);
        ConfigAttributeDefinition config = new ConfigAttributeDefinition("ROLE_B");
        
        assertEquals(RoleHierarchyVoter.ACCESS_GRANTED, voter.vote(auth, new Object(), config)); 
	}
}
