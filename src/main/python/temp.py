from HServerHelper import HServerHelper

hsHelper = HServerHelper("localhost", 7777, connect = True)
df = hsHelper.get_blocks(10, close_connection = True)
print(df)