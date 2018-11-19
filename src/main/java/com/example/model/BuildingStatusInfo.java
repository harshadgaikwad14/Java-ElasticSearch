/*
 * 
 */
package com.example.model;

// TODO: Auto-generated Javadoc
/**
 * The Class BuildingStatusInfo.
 */
public class BuildingStatusInfo {

	/** ChannelSequenceId. */
	private String channelSeqId = null;

	/** DomainSequenceId. */
	private String domainSeqId = null;

	/**
	 * Gets the channel seq id.
	 *
	 * @return the channel seq id
	 */
	public String getChannelSeqId() {
		return channelSeqId;
	}

	/**
	 * Sets the channel seq id.
	 *
	 * @param channelSeqId
	 *            the new channel seq id
	 */
	public void setChannelSeqId(String channelSeqId) {
		this.channelSeqId = channelSeqId;
	}

	/**
	 * Gets the domain seq id.
	 *
	 * @return the domain seq id
	 */
	public String getDomainSeqId() {
		return domainSeqId;
	}

	/**
	 * Sets the domain seq id.
	 *
	 * @param domainSeqId
	 *            the new domain seq id
	 */
	public void setDomainSeqId(String domainSeqId) {
		this.domainSeqId = domainSeqId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "StateInfoDAO [channelSeqId=" + channelSeqId + ", domainSeqId=" + domainSeqId + "]";
	}

}
