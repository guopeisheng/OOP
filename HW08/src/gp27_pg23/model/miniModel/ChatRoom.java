package gp27_pg23.model.miniModel;

import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.nio.file.Files;

import javax.swing.ImageIcon;

import common.DataPacketAlgoCmd;
import common.DataPacketChatRoom;
import common.IAddReceiverType;
import common.IChatRoom;
import common.ICmd2ModelAdapter;
import common.IComponentFactory;
import common.IExceptionStatusType;
import common.IFailureStatusType;
import common.IInstallCmdType;
import common.IReceiver;
import common.IRejectionStatusType;
import common.IRemoveReceiverType;
import common.IRequestCmdType;
import gp27_pg23.model.ChatRoomManager;
import gp27_pg23.model.cmd.CmdCommAddReceiver;
import gp27_pg23.model.cmd.CmdCommInstallCmd;
import gp27_pg23.model.cmd.CmdCommRemoveReceiver;
import gp27_pg23.model.cmd.CmdCommRequestCmd;
import gp27_pg23.model.cmd.CmdFailureStatus;
import gp27_pg23.model.cmd.CmdFile;
import gp27_pg23.model.cmd.CmdImage;
import gp27_pg23.model.cmd.CmdText;
import gp27_pg23.model.cmd.FileType;
import gp27_pg23.model.cmd.ImageType;
import gp27_pg23.model.cmd.TextType;
import provided.datapacket.DataPacketAlgo;

/**
 * The chat room - mini model
 * @author Gao Pan
 *
 */
public class ChatRoom implements IChatRoom {

	/**
	 * UID
	 */
	private static final long serialVersionUID = -5207620380615489899L;

	/**
	 * UUID
	 */
	private UUID uuid;

	/**
	 * Chat room name
	 */
	private String CRName;

	/**
	 * List of receivers
	 */
	private List<IReceiver> conns;

	/**
	 * Receiver of the local user
	 */
	private IReceiver self;

	/**
	 * Command manager
	 */
	private DataPacketAlgo<String, String> cmdMngr;

	/**
	 * Mini View Adapter
	 */
	private transient IChatWindowAdapter cwadpt;

	/**
	 * Command to model adapter
	 */
	private transient ICmd2ModelAdapter c2madpt;

	/**
	 * Construct with chat room name and reference to chat room manager
	 * @param name Chat room name
	 * @param crMngr Chat room manager
	 */
	public ChatRoom(String name, ChatRoomManager crMngr) {
		this.uuid = UUID.randomUUID();
		this.CRName = name;
		conns = new ArrayList<IReceiver>();
	}

	/**
	 * Construct with an existing chat room and reference to chat room manager
	 * @param cr existing chat room
	 * @param crMngr Chat room manager
	 */
	public ChatRoom(IChatRoom cr, ChatRoomManager crMngr) {
		this.uuid = cr.getUUID();
		this.CRName = cr.getName();
		conns = new ArrayList<IReceiver>();
		Collection<IReceiver> tmp = cr.getIReceiverStubs();
		for (IReceiver recv : tmp) {
			conns.add(recv);
		}
	}

	/**
	 * Add a receiver
	 * @param conn The receiver
	 */
	public void addConn(IReceiver conn) {
		conns.add(conn);
		cwadpt.updateUser(conns);
	}

	/**
	 * Get self stub
	 * @return The stub of self
	 */
	public IReceiver getSelfStub() {
		return self;
	}

	/**
	 * Joining a chat room
	 */
	public void newConn() {
		for (IReceiver c : conns) {
			try {
				c.receive(new DataPacketChatRoom<IAddReceiverType>(IAddReceiverType.class, new IAddReceiverType() {
					/**
					 * UID
					 */
					private static final long serialVersionUID = 2256004474761893835L;

					@Override
					public IReceiver getReceiverStub() {
						return self;
					}
				}, self));
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Send a data packet in a chat room
	 * @param dpType The data type of the data packet 
	 * @param text The parameter to construct the data packet
	 */
	public void sendDataPacket(String dpType, String text) {
		for (IReceiver conn : conns) {
			try {
				conn.receive(new DataPacketChatRoom<TextType>(TextType.class,
						new TextType("[" + dpType + " from " + self.getUserStub().getName() + "]"), self));
				switch (dpType) {
				case "Text":
					conn.receive(new DataPacketChatRoom<TextType>(TextType.class, new TextType(text), self));
					break;
				case "Image":
					conn.receive(new DataPacketChatRoom<ImageType>(ImageType.class, new ImageType(new ImageIcon(text)),
							self));
					break;
				case "File":
					File file = new File(text);
					byte[][] data = new byte[2][];
					data[0] = file.getName().getBytes();
					data[1] = Files.readAllBytes(file.toPath());
					conn.receive(new DataPacketChatRoom<FileType>(FileType.class, new FileType(data), self));
					break;
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public UUID getUUID() {
		return uuid;
	}

	@Override
	public String getName() {
		return CRName;
	}

	@Override
	public String toString() {
		return getName();
	}

	@Override
	public List<IReceiver> getIReceiverStubs() {
		return conns;
	}

	/**
	 * Remove a receiver with the UUID
	 * @param uuid The UUID to remove
	 */
	public void removeConn(UUID uuid) {
		IReceiver target = null;
		for (IReceiver c : conns) {
			try {
				if (c.getUUID().equals(uuid)) {
					target = c;
					break;
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}
		if (target != null)
			conns.remove(target);
		cwadpt.updateUser(conns);
	}

	/**
	 * Leave the chat room
	 */
	public void leave() {
		for (IReceiver c : conns) {
			if (c != self) {
				try {
					c.receive(new DataPacketChatRoom<IRemoveReceiverType>(IRemoveReceiverType.class,
							new IRemoveReceiverType() {

								/**
								 * UID
								 */
								private static final long serialVersionUID = 8406497453455646104L;

								@Override
								public IReceiver getReceiverStub() {
									return self;
								}

							}, self));
				} catch (RemoteException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Set the receiver of self
	 * @param conn The receiver of user self
	 */
	public void setSelf(IReceiver conn) {
		self = conn;
		newConn();
		conns.add(conn);
		initC2MAdpt();
		initCmdMngr();
		cwadpt.updateUser(conns);
	}

	/**
	 * Get the command manager
	 * @return The command manager
	 */
	public DataPacketAlgo<String, String> getCmdMngr() {
		return cmdMngr;
	}

	/**
	 * Set mini view adapter
	 * @param cwadpt The mini view adapter
	 */
	public void setAdpt(IChatWindowAdapter cwadpt) {
		this.cwadpt = cwadpt;

	}

	@Override
	public <T> void sendPacket(DataPacketChatRoom<T> data) {
		for (IReceiver recv : conns) {
			try {
				recv.receive(data);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public boolean addIReceiverStubLocally(IReceiver receiver) {
		conns.add(receiver);
		return false;
	}

	@Override
	public boolean removeIReceiverStubLocally(IReceiver receiver) {
		conns.remove(receiver);
		return false;
	}

	/**
	 * Initialize the command to model adapter
	 */
	private void initC2MAdpt() {
		c2madpt = new ICmd2ModelAdapter() {
			@Override
			public void appendMsg(String text, String name) {
				cwadpt.updateMsg(text);

			}

			@Override
			public void buildScrollableComponent(IComponentFactory fac, String label) {
				Component c = fac.makeComponent();
				cwadpt.popWindow(c);
			}

			@Override
			public void buildNonScrollableComponent(IComponentFactory fac, String label) {
				Component c = fac.makeComponent();
				cwadpt.popWindow(c);
			}

		};
	}

	/**
	 * Initialize command manager
	 */
	private void initCmdMngr() {

		cmdMngr = new DataPacketAlgo<String, String>(new DataPacketAlgoCmd<Object>() {

			/**
			 * UID 
			 */
			private static final long serialVersionUID = -8164658560485524336L;

			@Override
			public String apply(Class<?> index, DataPacketChatRoom<Object> host, String... params) {
				try {
					host.getSender().receive(
							new DataPacketChatRoom<IRequestCmdType>(IRequestCmdType.class, new IRequestCmdType() {

								/**
								 * UID
								 */
								private static final long serialVersionUID = 2901583931947226654L;

								@Override
								public Class<?> getCmdId() {
									return index;
								}
							}, self));
				} catch (RemoteException e) {
					e.printStackTrace();
				}
				return "DEFAULT";
			}

			@Override
			public void setCmd2ModelAdpt(ICmd2ModelAdapter cmd2ModelAdpt) {
			}

		});

		//Install commands
		cmdMngr.setCmd(IAddReceiverType.class, new CmdCommAddReceiver(this));
		cmdMngr.setCmd(IRemoveReceiverType.class, new CmdCommRemoveReceiver(this));
		cmdMngr.setCmd(IRequestCmdType.class, new CmdCommRequestCmd(cmdMngr, self));
		cmdMngr.setCmd(IInstallCmdType.class, new CmdCommInstallCmd(cmdMngr, self, c2madpt));
		cmdMngr.setCmd(IFailureStatusType.class, new CmdFailureStatus(c2madpt));
		cmdMngr.setCmd(IRejectionStatusType.class, new CmdFailureStatus(c2madpt));
		cmdMngr.setCmd(IExceptionStatusType.class, new CmdFailureStatus(c2madpt));

		cmdMngr.setCmd(TextType.class, new CmdText(c2madpt));
		cmdMngr.setCmd(ImageType.class, new CmdImage(c2madpt));
		cmdMngr.setCmd(FileType.class, new CmdFile(c2madpt));
	}
}
