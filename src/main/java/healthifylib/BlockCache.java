package healthifylib;

public interface BlockCache {
	int curCachedBlocksNum();
	
	// caches all the blocks
	void cacheBlocks(Block[] blocks);
	
	// caches a single block
	void cacheBlock(Block block);
	
	// get a CSVfied string representation of the first N CachedBlocks
	String getFirstNBlocks(int numBlocks);
}
