
package jax;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the jax package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _EmailUnique_QNAME = new QName("http://jax/", "emailUnique");
    private final static QName _AjouterUtilisateurResponse_QNAME = new QName("http://jax/", "ajouterUtilisateurResponse");
    private final static QName _UtilisateurExisteResponse_QNAME = new QName("http://jax/", "utilisateurExisteResponse");
    private final static QName _GetUtilisateurResponse_QNAME = new QName("http://jax/", "getUtilisateurResponse");
    private final static QName _AjouterUtilisateur_QNAME = new QName("http://jax/", "ajouterUtilisateur");
    private final static QName _UtilisateurExiste_QNAME = new QName("http://jax/", "utilisateurExiste");
    private final static QName _GetUtilisateur_QNAME = new QName("http://jax/", "getUtilisateur");
    private final static QName _EmailUniqueResponse_QNAME = new QName("http://jax/", "emailUniqueResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: jax
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link EmailUniqueResponse }
     * 
     */
    public EmailUniqueResponse createEmailUniqueResponse() {
        return new EmailUniqueResponse();
    }

    /**
     * Create an instance of {@link UtilisateurExiste }
     * 
     */
    public UtilisateurExiste createUtilisateurExiste() {
        return new UtilisateurExiste();
    }

    /**
     * Create an instance of {@link GetUtilisateur }
     * 
     */
    public GetUtilisateur createGetUtilisateur() {
        return new GetUtilisateur();
    }

    /**
     * Create an instance of {@link GetUtilisateurResponse }
     * 
     */
    public GetUtilisateurResponse createGetUtilisateurResponse() {
        return new GetUtilisateurResponse();
    }

    /**
     * Create an instance of {@link AjouterUtilisateur }
     * 
     */
    public AjouterUtilisateur createAjouterUtilisateur() {
        return new AjouterUtilisateur();
    }

    /**
     * Create an instance of {@link EmailUnique }
     * 
     */
    public EmailUnique createEmailUnique() {
        return new EmailUnique();
    }

    /**
     * Create an instance of {@link AjouterUtilisateurResponse }
     * 
     */
    public AjouterUtilisateurResponse createAjouterUtilisateurResponse() {
        return new AjouterUtilisateurResponse();
    }

    /**
     * Create an instance of {@link UtilisateurExisteResponse }
     * 
     */
    public UtilisateurExisteResponse createUtilisateurExisteResponse() {
        return new UtilisateurExisteResponse();
    }

    /**
     * Create an instance of {@link UtilisateurPOJO }
     * 
     */
    public UtilisateurPOJO createUtilisateurPOJO() {
        return new UtilisateurPOJO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmailUnique }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jax/", name = "emailUnique")
    public JAXBElement<EmailUnique> createEmailUnique(EmailUnique value) {
        return new JAXBElement<EmailUnique>(_EmailUnique_QNAME, EmailUnique.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AjouterUtilisateurResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jax/", name = "ajouterUtilisateurResponse")
    public JAXBElement<AjouterUtilisateurResponse> createAjouterUtilisateurResponse(AjouterUtilisateurResponse value) {
        return new JAXBElement<AjouterUtilisateurResponse>(_AjouterUtilisateurResponse_QNAME, AjouterUtilisateurResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UtilisateurExisteResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jax/", name = "utilisateurExisteResponse")
    public JAXBElement<UtilisateurExisteResponse> createUtilisateurExisteResponse(UtilisateurExisteResponse value) {
        return new JAXBElement<UtilisateurExisteResponse>(_UtilisateurExisteResponse_QNAME, UtilisateurExisteResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUtilisateurResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jax/", name = "getUtilisateurResponse")
    public JAXBElement<GetUtilisateurResponse> createGetUtilisateurResponse(GetUtilisateurResponse value) {
        return new JAXBElement<GetUtilisateurResponse>(_GetUtilisateurResponse_QNAME, GetUtilisateurResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AjouterUtilisateur }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jax/", name = "ajouterUtilisateur")
    public JAXBElement<AjouterUtilisateur> createAjouterUtilisateur(AjouterUtilisateur value) {
        return new JAXBElement<AjouterUtilisateur>(_AjouterUtilisateur_QNAME, AjouterUtilisateur.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UtilisateurExiste }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jax/", name = "utilisateurExiste")
    public JAXBElement<UtilisateurExiste> createUtilisateurExiste(UtilisateurExiste value) {
        return new JAXBElement<UtilisateurExiste>(_UtilisateurExiste_QNAME, UtilisateurExiste.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUtilisateur }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jax/", name = "getUtilisateur")
    public JAXBElement<GetUtilisateur> createGetUtilisateur(GetUtilisateur value) {
        return new JAXBElement<GetUtilisateur>(_GetUtilisateur_QNAME, GetUtilisateur.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EmailUniqueResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://jax/", name = "emailUniqueResponse")
    public JAXBElement<EmailUniqueResponse> createEmailUniqueResponse(EmailUniqueResponse value) {
        return new JAXBElement<EmailUniqueResponse>(_EmailUniqueResponse_QNAME, EmailUniqueResponse.class, null, value);
    }

}
