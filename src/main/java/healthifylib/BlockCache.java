package healthifylib;

public interface BlockCache {
	int curCachedBlocksNum();
	
	/**
	 *  caches all the blocks
	 * @param blocks: array consisting of blocks to cache
	 */
	void cacheBlocks(Block[] blocks);
	
	/**
	 * caches a single block
	 * @param block: the block to cache
	 */
	void cacheBlock(Block block);
	
	/**
	 *  get a header included CSVfied string representation of the first numBlocks CachedBlocks
	 * @param numBlocks: Number of blocks to cache
	 * @return complete header included csv string
	 */
	String getNBlocks(int numBlocks);
	
	/**
	 *  get a header included CSVfied string representation of numBlocks number of CachedBlocks, starting from startBlock
	 * @param numBlocks: Number of blocks to cache
	 * @param startBlock: The number of starting block (1-index based), the first block is numbered 1
	 * @return complete header included csv string
	 */
	String getNBlocks(int numBlocks, int startBlock);
}
