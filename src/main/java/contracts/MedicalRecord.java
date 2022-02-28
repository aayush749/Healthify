package contracts;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.DynamicArray;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint8;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.4.1.
 */
@SuppressWarnings("rawtypes")
public class MedicalRecord extends Contract {
    public static final String BINARY = "60806040526000600160006101000a81548160ff021916908315150217905550600160035534801561003057600080fd5b50600060058190555060006004819055506106d7806100506000396000f3fe608060405234801561001057600080fd5b50600436106100885760003560e01c80638d0605191161005b5780638d060519146100ff578063945497dc1461011b578063bfdbf3b01461014b578063f9874db91461016957610088565b806328dd8d5f1461008d5780632c5ee501146100a957806331da4036146100c757806346b33a9e146100e3575b600080fd5b6100a760048036038101906100a29190610406565b610187565b005b6100b16102b3565b6040516100be919061044c565b60405180910390f35b6100e160048036038101906100dc9190610406565b6102bc565b005b6100fd60048036038101906100f89190610493565b6102bf565b005b61011960048036038101906101149190610493565b6102c9565b005b61013560048036038101906101309190610406565b610302565b60405161014291906104db565b60405180910390f35b610153610345565b604051610160919061044c565b60405180910390f35b61017161034f565b60405161017e919061061c565b60405180910390f35b6000600182608381111561019e5761019d610522565b5b6101a8919061066d565b6001901b905060001515600660008460838111156101c9576101c8610522565b5b60838111156101db576101da610522565b5b815260200190815260200160002060009054906101000a900460ff16151514156102af57806005541760058190555060016006600084608381111561022357610222610522565b5b608381111561023557610234610522565b5b815260200190815260200160002060006101000a81548160ff02191690831515021790555060028290806001815401808255809150506001900390600052602060002090602091828204019190069091909190916101000a81548160ff021916908360838111156102a9576102a8610522565b5b02179055505b5050565b60008054905090565b50565b8060048190555050565b600160009054906101000a900460ff166102ff578060008190555060018060006101000a81548160ff0219169083151502179055505b50565b60008060009050600060018460838111156103205761031f610522565b5b61032a919061066d565b6001901b905060055481169150600082141592505050919050565b6000600454905090565b606060028054806020026020016040519081016040528092919081815260200182805480156103d257602002820191906000526020600020906000905b82829054906101000a900460ff1660838111156103ac576103ab610522565b5b8152602001906001019060208260000104928301926001038202915080841161038c5790505b5050505050905090565b600080fd5b608481106103ee57600080fd5b50565b600081359050610400816103e1565b92915050565b60006020828403121561041c5761041b6103dc565b5b600061042a848285016103f1565b91505092915050565b6000819050919050565b61044681610433565b82525050565b6000602082019050610461600083018461043d565b92915050565b61047081610433565b811461047b57600080fd5b50565b60008135905061048d81610467565b92915050565b6000602082840312156104a9576104a86103dc565b5b60006104b78482850161047e565b91505092915050565b60008115159050919050565b6104d5816104c0565b82525050565b60006020820190506104f060008301846104cc565b92915050565b600081519050919050565b600082825260208201905092915050565b6000819050602082019050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602160045260246000fd5b6084811061056257610561610522565b5b50565b600081905061057382610551565b919050565b600061058382610565565b9050919050565b61059381610578565b82525050565b60006105a5838361058a565b60208301905092915050565b6000602082019050919050565b60006105c9826104f6565b6105d38185610501565b93506105de83610512565b8060005b8381101561060f5781516105f68882610599565b9750610601836105b1565b9250506001810190506105e2565b5085935050505092915050565b6000602082019050818103600083015261063681846105be565b905092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b600061067882610433565b915061068383610433565b9250828210156106965761069561063e565b5b82820390509291505056fea2646970667358221220ffb30e4159458fe228db778f98b9c65075fd0aee64e3d6a71b0da6ddfff545f264736f6c634300080b0033";

    public static final String FUNC_CLEARSYMPTOMFIELD = "clearSymptomField";

    public static final String FUNC_GETPID = "getPID";

    public static final String FUNC_GETPROGNOSIS = "getPrognosis";

    public static final String FUNC_HASSYMPTOM = "hasSymptom";

    public static final String FUNC_LISTALLSYMPTOMS = "listAllSymptoms";

    public static final String FUNC_SETPID = "setPID";

    public static final String FUNC_SETPROGNOSIS = "setPrognosis";

    public static final String FUNC_SETSYMPTOMFIELD = "setSymptomField";

    @Deprecated
    protected MedicalRecord(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected MedicalRecord(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected MedicalRecord(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected MedicalRecord(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<BigInteger> getPID() {
        final Function function = new Function(FUNC_GETPID, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<BigInteger> getPrognosis() {
        final Function function = new Function(FUNC_GETPROGNOSIS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Boolean> hasSymptom(BigInteger symptom) {
        final Function function = new Function(FUNC_HASSYMPTOM, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(symptom)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeRemoteCallSingleValueReturn(function, Boolean.class);
    }

    public RemoteFunctionCall<List> listAllSymptoms() {
        final Function function = new Function(FUNC_LISTALLSYMPTOMS, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Uint8>>() {}));
        return new RemoteFunctionCall<List>(function,
                new Callable<List>() {
                    @Override
                    @SuppressWarnings("unchecked")
                    public List call() throws Exception {
                        List<Type> result = (List<Type>) executeCallSingleValueReturn(function, List.class);
                        return convertToNative(result);
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> setPID(BigInteger new_pID) {
        final Function function = new Function(
                FUNC_SETPID, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(new_pID)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setPrognosis(BigInteger newPrognosisVal) {
        final Function function = new Function(
                FUNC_SETPROGNOSIS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(newPrognosisVal)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> setSymptomField(BigInteger symptom) {
        final Function function = new Function(
                FUNC_SETSYMPTOMFIELD, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint8(symptom)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static MedicalRecord load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new MedicalRecord(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static MedicalRecord load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new MedicalRecord(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static MedicalRecord load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new MedicalRecord(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static MedicalRecord load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new MedicalRecord(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<MedicalRecord> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(MedicalRecord.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    public static RemoteCall<MedicalRecord> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(MedicalRecord.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<MedicalRecord> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(MedicalRecord.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<MedicalRecord> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(MedicalRecord.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
