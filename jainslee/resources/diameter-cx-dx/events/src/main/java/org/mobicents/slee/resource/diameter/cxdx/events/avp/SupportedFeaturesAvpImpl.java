/*
 * Mobicents, Communications Middleware
 * 
 * Copyright (c) 2008, Red Hat Middleware LLC or third-party contributors as
 * indicated by the @author tags or express copyright attribution
 * statements applied by the authors.  All third-party contributions are
 * distributed under license by Red Hat Middleware LLC.
 *
 * This copyrighted material is made available to anyone wishing to use, modify,
 * copy, or redistribute it subject to the terms and conditions of the GNU
 * Lesser General Public License, as published by the Free Software Foundation.
 *
 * This program is distributed in the hope that it will be useful, but 
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
 * or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Lesser General Public License
 * for more details.
 *
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this distribution; if not, write to:
 * Free Software Foundation, Inc.
 * 51 Franklin Street, Fifth Floor
 *
 * Boston, MA  02110-1301  USA
 */
package org.mobicents.slee.resource.diameter.cxdx.events.avp;

import net.java.slee.resource.diameter.cxdx.events.avp.DiameterCxDxAvpCodes;
import net.java.slee.resource.diameter.cxdx.events.avp.SupportedFeaturesAvp;

import org.jdiameter.api.Avp;
import org.mobicents.slee.resource.diameter.base.events.avp.GroupedAvpImpl;

/**
 * 
 * Implementation of AVP: {@link SupportedFeaturesAvp} interface.
 * 
 * @author <a href="mailto:baranowb@gmail.com"> Bartosz Baranowski </a>
 * @author <a href="mailto:brainslog@gmail.com"> Alexandre Mendonca </a>
 * @author Erick Svenson
 */
public class SupportedFeaturesAvpImpl extends GroupedAvpImpl implements SupportedFeaturesAvp {

  public SupportedFeaturesAvpImpl() {
    super();
  }

  /**
   * 
   * @param code
   * @param vendorId
   * @param mnd
   * @param prt
   * @param value
   */
  public SupportedFeaturesAvpImpl(int code, long vendorId, int mnd, int prt, byte[] value) {
    super(code, vendorId, mnd, prt, value);
  }

  /*
   * (non-Javadoc)
   * @see net.java.slee.resource.diameter.sh.events.avp.SupportedFeaturesAvp#getFeatureList()
   */
  public long getFeatureList() {
    return getAvpAsUnsigned32(DiameterCxDxAvpCodes.FEATURE_LIST, DiameterCxDxAvpCodes.CXDX_VENDOR_ID);
  }

  /*
   * (non-Javadoc)
   * @see net.java.slee.resource.diameter.sh.events.avp.SupportedFeaturesAvp#hasFeatureList()
   */
  public boolean hasFeatureList() {
    return hasAvp(DiameterCxDxAvpCodes.FEATURE_LIST, DiameterCxDxAvpCodes.CXDX_VENDOR_ID);
  }

  /*
   * (non-Javadoc)
   * @see net.java.slee.resource.diameter.sh.events.avp.SupportedFeaturesAvp#setFeatureList(long)
   */
  public void setFeatureList(long featureList) {
    addAvp(DiameterCxDxAvpCodes.FEATURE_LIST, DiameterCxDxAvpCodes.CXDX_VENDOR_ID, featureList);
  }

  /*
   * (non-Javadoc)
   * @see net.java.slee.resource.diameter.sh.events.avp.SupportedFeaturesAvp#getFeatureListId()
   */
  public long getFeatureListId() {
    return getAvpAsUnsigned32(DiameterCxDxAvpCodes.FEATURE_LIST_ID, DiameterCxDxAvpCodes.CXDX_VENDOR_ID);
  }

  /*
   * (non-Javadoc)
   * @see net.java.slee.resource.diameter.sh.events.avp.SupportedFeaturesAvp#hasFeatureListId()
   */
  public boolean hasFeatureListId() {
    return hasAvp(DiameterCxDxAvpCodes.FEATURE_LIST_ID, DiameterCxDxAvpCodes.CXDX_VENDOR_ID);
  }

  /*
   * (non-Javadoc)
   * @see net.java.slee.resource.diameter.sh.events.avp.SupportedFeaturesAvp#setFeatureListId(long)
   */
  public void setFeatureListId(long featureListId) {
    addAvp(DiameterCxDxAvpCodes.FEATURE_LIST_ID, DiameterCxDxAvpCodes.CXDX_VENDOR_ID, featureListId);
  }

  /*
   * (non-Javadoc)
   * @see net.java.slee.resource.diameter.sh.events.avp.SupportedFeaturesAvp#hasVendorId()
   */
  public boolean hasVendorId() {
    return hasAvp(Avp.VENDOR_ID);
  }

  /*
   * (non-Javadoc)
   * @see net.java.slee.resource.diameter.sh.events.avp.SupportedFeaturesAvp#setVendorId(long)
   */
  public void setVendorId(long vendorId) {
    addAvp(Avp.VENDOR_ID, vendorId);
  }

}