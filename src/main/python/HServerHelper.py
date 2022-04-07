from socket import AF_INET, socket, AddressFamily, SOCK_STREAM
from pandas import DataFrame, read_csv

class HServerHelper:
    """
        An HServerHelper helps to connect and obtain data blocks from the HServer.
    """
    def __init__(self, ip_address: str, port_number: int, connect: bool = False) -> None:
        """
            Creates an instance of an HServerHelper.
            Use this to connect to HServer and obtain data blocks.
        """

        if ip_address != None and port_number != None:
            self.__ip = ip_address
            self.__port = port_number
            self.__is_socket_created = False
            self.__is_socket_connected = False
            
            # create socket object
            self.__socket = socket(AF_INET, SOCK_STREAM)
            self.__is_socket_created = True
        
        if connect:
            self.connect()
    
    def connect(self) -> None:
        """
            Connect to the HServer for obtaining data blocks
        """
        if not self.__is_socket_connected:
            try:
                self.__socket.connect((self.__ip, self.__port))
                self.__is_socket_connected = True
            except Exception as e:
                print(f"Could not connect to host: {self.__ip}. [Error]: {e}")
    
    def close(self) -> None:
        """
            Closes the connection to the HServer
        """
        if self.__is_socket_connected:
            try:
                self.__socket.close()
                self.__is_socket_connected = False
            except Exception as e:
                print(f"Could not close connection to host: {self.__ip}, might already be closed. [Error] {e}")
    
    def get_blocks(self, count: int, close_connection: bool = True) -> DataFrame :
        """
            Get the desired number of blocks from the HServer
            count : The number of blocks you desire
            close_connection: If set to true, the connection to the HServer will be closed once blocks' information is collected

            Returns: A pandas dataframe object created from the requested 'count' number of blocks
        """

        # connect the socket if not connected
        if not self.__is_socket_connected:
            self.connect()

        # submit the requested for obtaining 'count' number of blocks to the HServer
        try:
            self.__socket.send(bytes(f"GetBlocks {count}\n", "utf-8"))

            with self.__socket.makefile('r', newline = '\r\n') as sock_file:
                block_df = read_csv(sock_file)
            
            # close the connection in case of successful read
            if close_connection:
                self.close()

            return block_df
        
        except Exception as e:
            raise IOError(f"Could not read from HServer. [Error]: {e}")
        
        finally:
            if close_connection:
                self.close()


