package healthifylib;

import java.util.ArrayList;
import java.util.List;

public class SimpleBlockCache implements BlockCache {
	// determines how many blocks can be cached on a single thread
	private static final int SINGLE_THREAD_BLOCK_CACHE_THRESHOLD = 10;
	private String m_BlockHeaderString;
	
	private List<Block> m_Cache;
	
	public SimpleBlockCache()
	{
		m_Cache = new ArrayList<Block>();
	}
	
	@Override
	public int curCachedBlocksNum() {
		return m_Cache.size();
	}

	@Override
	public void cacheBlocks(Block[] blocks) {
		if (blocks != null) {
			// initialize block header if not already initialized
			InitBlockHeader();
			
			if(blocks.length > SINGLE_THREAD_BLOCK_CACHE_THRESHOLD) {
				// TODO: do caching on other thread asynchronously
			} else {
				// do caching on the current thread
				for(Block block : blocks) {
					m_Cache.add(block);
				}
			}
			
		}
	}

	private void InitBlockHeader() {
		// initialize block header if not already initialized
		if (m_BlockHeaderString.isBlank() && m_Cache.size() != 0) {
			m_BlockHeaderString = m_Cache.get(0).getBlockHeaderCSV();
		}
	}

	@Override
	public String getFirstNBlocks(int numBlocks) {
		String blockCsvStr = m_BlockHeaderString;
		if (numBlocks >= 0) {
			for(int curBlockID = 1; curBlockID <= numBlocks; curBlockID++) {
				blockCsvStr += m_Cache.get(curBlockID).getCSVString();
			}
		}
		return blockCsvStr;
	}

	@Override
	public void cacheBlock(Block block) {
		// initialize block header if not already initialized
		InitBlockHeader();
		
		m_Cache.add(block);
	}
}
