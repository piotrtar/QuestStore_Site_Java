//package com.school.models;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//class ArtifactTest {
//
//    private Artifact artifact;
//
//    @BeforeEach
//    public void before() {
//        artifact = new Artifact("artifact");
//    }
//
//    @Test
//    public void testGetNameReturensExpectedValue() {
//        String actual = this.artifact.getName();
//        assertEquals("artifact", actual);
//    }
//
//    @Test
//    public void testSetNameChangesNameToExpectedValue() {
//        this.artifact.setName("random");
//        String actual = this.artifact.getName();
//        assertEquals("random", actual);
//    }
//
//    @Test
//    public void testToStringReturnsExpectedString() {
//        String actual = this.artifact.toString();
//        assertEquals("artifact", actual);
//    }
//
//}