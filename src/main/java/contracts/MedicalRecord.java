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
    public static final String BINARY = "60806040526000600160006101000a81548160ff021916908315150217905550600160035534801561003057600080fd5b5060006005819055506000600460006101000a81548160ff021916908315150217905550610710806100636000396000f3fe608060405234801561001057600080fd5b506004361061007d5760003560e01c806331da40361161005b57806331da4036146100d85780638d060519146100f4578063945497dc14610110578063f9874db9146101405761007d565b806318a156bc1461008257806328dd8d5f1461009e5780632c5ee501146100ba575b600080fd5b61009c600480360381019061009791906103f9565b61015e565b005b6100b860048036038101906100b3919061044b565b61017b565b005b6100c26102a7565b6040516100cf9190610491565b60405180910390f35b6100f260048036038101906100ed919061044b565b6102b0565b005b61010e600480360381019061010991906104d8565b6102b3565b005b61012a6004803603810190610125919061044b565b6102ec565b6040516101379190610514565b60405180910390f35b61014861032f565b6040516101559190610655565b60405180910390f35b80600460006101000a81548160ff02191690831515021790555050565b600060018260838111156101925761019161055b565b5b61019c91906106a6565b6001901b905060001515600660008460838111156101bd576101bc61055b565b5b60838111156101cf576101ce61055b565b5b815260200190815260200160002060009054906101000a900460ff16151514156102a35780600554176005819055506001600660008460838111156102175761021661055b565b5b60838111156102295761022861055b565b5b815260200190815260200160002060006101000a81548160ff02191690831515021790555060028290806001815401808255809150506001900390600052602060002090602091828204019190069091909190916101000a81548160ff0219169083608381111561029d5761029c61055b565b5b02179055505b5050565b60008054905090565b50565b600160009054906101000a900460ff166102e9578060008190555060018060006101000a81548160ff0219169083151502179055505b50565b600080600090506000600184608381111561030a5761030961055b565b5b61031491906106a6565b6001901b905060055481169150600082141592505050919050565b606060028054806020026020016040519081016040528092919081815260200182805480156103b257602002820191906000526020600020906000905b82829054906101000a900460ff16608381111561038c5761038b61055b565b5b8152602001906001019060208260000104928301926001038202915080841161036c5790505b5050505050905090565b600080fd5b60008115159050919050565b6103d6816103c1565b81146103e157600080fd5b50565b6000813590506103f3816103cd565b92915050565b60006020828403121561040f5761040e6103bc565b5b600061041d848285016103e4565b91505092915050565b6084811061043357600080fd5b50565b60008135905061044581610426565b92915050565b600060208284031215610461576104606103bc565b5b600061046f84828501610436565b91505092915050565b6000819050919050565b61048b81610478565b82525050565b60006020820190506104a66000830184610482565b92915050565b6104b581610478565b81146104c057600080fd5b50565b6000813590506104d2816104ac565b92915050565b6000602082840312156104ee576104ed6103bc565b5b60006104fc848285016104c3565b91505092915050565b61050e816103c1565b82525050565b60006020820190506105296000830184610505565b92915050565b600081519050919050565b600082825260208201905092915050565b6000819050602082019050919050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052602160045260246000fd5b6084811061059b5761059a61055b565b5b50565b60008190506105ac8261058a565b919050565b60006105bc8261059e565b9050919050565b6105cc816105b1565b82525050565b60006105de83836105c3565b60208301905092915050565b6000602082019050919050565b60006106028261052f565b61060c818561053a565b93506106178361054b565b8060005b8381101561064857815161062f88826105d2565b975061063a836105ea565b92505060018101905061061b565b5085935050505092915050565b6000602082019050818103600083015261066f81846105f7565b905092915050565b7f4e487b7100000000000000000000000000000000000000000000000000000000600052601160045260246000fd5b60006106b182610478565b91506106bc83610478565b9250828210156106cf576106ce610677565b5b82820390509291505056fea2646970667358221220511e7e21e9b6eace5278949e4cd6f698dd92baef4d5f8d288bbe87ba9494028c64736f6c634300080b0033";

    public static final String FUNC_CLEARSYMPTOMFIELD = "clearSymptomField";

    public static final String FUNC_GETPID = "getPID";

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

    public RemoteFunctionCall<TransactionReceipt> setPrognosis(Boolean newPrognosisVal) {
        final Function function = new Function(
                FUNC_SETPROGNOSIS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Bool(newPrognosisVal)), 
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
