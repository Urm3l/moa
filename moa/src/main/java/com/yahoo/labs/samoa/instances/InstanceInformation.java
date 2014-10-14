/*
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * 	        http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific
 * language governing permissions and limitations under the
 * License.  
 */
package com.yahoo.labs.samoa.instances;

import java.io.Serializable;
import java.util.List;

/**
 * The Class InstanceInformation.
 *
 * @author abifet
 */
public class InstanceInformation implements Serializable {

    /**
     * The dataset's name.
     */
    protected String relationName;

    protected AttributesInformation attributesInformation;

    /**
     * The class index.
     */
    protected int classIndex = Integer.MAX_VALUE; //By default is multilabel

    /**
     * Range for multi-label instances.
     */
    protected Range range;

    public Attribute inputAttribute(int w) {
        return this.attributesInformation.attribute(inputAttributeIndex(w));
    }

    public Attribute outputAttribute(int w) {
        return this.attributesInformation.attribute(outputAttributeIndex(w));
    }

    /**
     * Instantiates a new instance information.
     *
     * @param chunk the chunk
     */
    public InstanceInformation(InstanceInformation chunk) {
        this.relationName = chunk.relationName;
        this.attributesInformation = chunk.attributesInformation;
        this.classIndex = chunk.classIndex;
    }

    /**
     * Instantiates a new instance information.
     *
     * @param st the st
     * @param v the v
     */
    public InstanceInformation(String st, List<Attribute> input) {
        this.relationName = st;
        this.attributesInformation = new AttributesInformation(input, input.size());
    }

    /**
     * Instantiates a new instance information.
     */
    public InstanceInformation() {
        this.relationName = null;
        this.attributesInformation = null;
    }

    //Information Instances
    /* (non-Javadoc)
     * @see com.yahoo.labs.samoa.instances.InstanceInformationInterface#setRelationName(java.lang.String)
     */
    public void setRelationName(String string) {
        this.relationName = string;
    }

    /* (non-Javadoc)
     * @see com.yahoo.labs.samoa.instances.InstanceInformationInterface#getRelationName()
     */
    public String getRelationName() {
        return this.relationName;
    }

    /* (non-Javadoc)
     * @see com.yahoo.labs.samoa.instances.InstanceInformationInterface#classIndex()
     */
    public int classIndex() {
        return this.classIndex;
    }

    /* (non-Javadoc)
     * @see com.yahoo.labs.samoa.instances.InstanceInformationInterface#setClassIndex(int)
     */
    public void setClassIndex(int classIndex) {
        this.classIndex = classIndex;
    }

    /* (non-Javadoc)
     * @see com.yahoo.labs.samoa.instances.InstanceInformationInterface#classAttribute()
     */
    public Attribute classAttribute() {
        return this.attribute(this.classIndex());
    }

    /* (non-Javadoc)
     * @see com.yahoo.labs.samoa.instances.InstanceInformationInterface#numAttributes()
     */
    public int numAttributes() {
        return this.attributesInformation.numberAttributes;
    }

    /* (non-Javadoc)
     * @see com.yahoo.labs.samoa.instances.InstanceInformationInterface#attribute(int)
     */
    public Attribute attribute(int w) {
        return this.attributesInformation.attribute(w);
    }

    /* (non-Javadoc)
     * @see com.yahoo.labs.samoa.instances.InstanceInformationInterface#numClasses()
     */
    public int numClasses() {
        return this.attributesInformation.attribute(classIndex()).numValues();
    }

    /* (non-Javadoc)
     * @see com.yahoo.labs.samoa.instances.InstanceInformationInterface#deleteAttributeAt(java.lang.Integer)
     */
    public void deleteAttributeAt(Integer integer) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    /* (non-Javadoc)
     * @see com.yahoo.labs.samoa.instances.InstanceInformationInterface#insertAttributeAt(com.yahoo.labs.samoa.instances.Attribute, int)
     */
    public void insertAttributeAt(Attribute attribute, int i) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    public void setAttributes(List<Attribute> v) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int inputAttributeIndex(int index) {
        int ret = 0;
        if (classIndex == Integer.MAX_VALUE) {//Multi Label

        } else { //Single Label
            ret = classIndex() > index ? index : index + 1;
        }
        return ret;
    }

    public int outputAttributeIndex(int attributeIndex) {
        int ret = 0;
        if (classIndex == Integer.MAX_VALUE) {//Multi Label

        } else { //Single Label
            ret = classIndex;
        }
        return ret;
    }

    int numInputAttributes() {
        int ret = 0;
        if (classIndex == Integer.MAX_VALUE) {//Multi Label

        } else { //Single Label
            ret = this.numAttributes() - 1;
        }
        return ret;
    }

    int numOutputAttributes() {
        int ret = 0;
        if (classIndex == Integer.MAX_VALUE) {//Multi Label

        } else { //Single Label
            ret = 1;
        }
        return ret;
    }

    void setRangeOutputIndices(Range range) {
        this.setClassIndex(Integer.MAX_VALUE);
        this.range = range;
    }

}
