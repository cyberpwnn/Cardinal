package ht.firefig.cardinal.sim;

import org.bukkit.Location;
import org.bukkit.block.BlockFace;
import org.bukkit.util.Vector;

import ninja.bytecode.shuriken.collections.GList;

/**
 * Vector utilities
 *
 * @author cyberpwn
 */
public class VectorMath
{
	/**
	 * Get all SIMPLE block faces from a more specific block face (SOUTH_EAST) =
	 * (south, east)
	 *
	 * @param f
	 *            the block face
	 * @return multiple faces, or one if the face is already simple
	 */
	public static GList<BlockFace> split(BlockFace f)
	{
		GList<BlockFace> faces = new GList<BlockFace>();

		switch(f)
		{
			case DOWN:
				faces.add(BlockFace.DOWN);
				break;
			case EAST:
				faces.add(BlockFace.EAST);
				break;
			case EAST_NORTH_EAST:
				faces.add(BlockFace.EAST);
				faces.add(BlockFace.EAST);
				faces.add(BlockFace.NORTH);
				break;
			case EAST_SOUTH_EAST:
				faces.add(BlockFace.EAST);
				faces.add(BlockFace.EAST);
				faces.add(BlockFace.SOUTH);
				break;
			case NORTH:
				faces.add(BlockFace.NORTH);
				break;
			case NORTH_EAST:
				faces.add(BlockFace.NORTH);
				faces.add(BlockFace.EAST);
				break;
			case NORTH_NORTH_EAST:
				faces.add(BlockFace.NORTH);
				faces.add(BlockFace.NORTH);
				faces.add(BlockFace.EAST);
				break;
			case NORTH_NORTH_WEST:
				faces.add(BlockFace.NORTH);
				faces.add(BlockFace.NORTH);
				faces.add(BlockFace.WEST);
				break;
			case NORTH_WEST:
				faces.add(BlockFace.NORTH);
				faces.add(BlockFace.WEST);
				break;
			case SELF:
				faces.add(BlockFace.SELF);
				break;
			case SOUTH:
				faces.add(BlockFace.SOUTH);
				break;
			case SOUTH_EAST:
				faces.add(BlockFace.SOUTH);
				faces.add(BlockFace.EAST);
				break;
			case SOUTH_SOUTH_EAST:
				faces.add(BlockFace.SOUTH);
				faces.add(BlockFace.SOUTH);
				faces.add(BlockFace.EAST);
				break;
			case SOUTH_SOUTH_WEST:
				faces.add(BlockFace.SOUTH);
				faces.add(BlockFace.SOUTH);
				faces.add(BlockFace.WEST);
				break;
			case SOUTH_WEST:
				faces.add(BlockFace.SOUTH);
				faces.add(BlockFace.WEST);
				break;
			case UP:
				faces.add(BlockFace.UP);
				break;
			case WEST:
				faces.add(BlockFace.WEST);
				break;
			case WEST_NORTH_WEST:
				faces.add(BlockFace.WEST);
				faces.add(BlockFace.WEST);
				faces.add(BlockFace.NORTH);
				break;
			case WEST_SOUTH_WEST:
				faces.add(BlockFace.WEST);
				faces.add(BlockFace.WEST);
				faces.add(BlockFace.SOUTH);
				break;
			default:
				break;
		}

		return faces;
	}

	/**
	 * Get a normalized vector going from a location to another
	 *
	 * @param from
	 *            from here
	 * @param to
	 *            to here
	 * @return the normalized vector direction
	 */
	public static Vector direction(Location from, Location to)
	{
		return to.subtract(from).toVector().normalize();
	}

	public static Vector directionNoNormal(Location from, Location to)
	{
		return to.subtract(from).toVector();
	}

	/**
	 * Get the vector direction from the yaw and pitch
	 *
	 * @param yaw
	 *            the yaw
	 * @param pitch
	 *            the pitch
	 * @return the vector
	 */
	public static Vector toVector(float yaw, float pitch)
	{
		return new Vector(Math.cos(pitch) * Math.cos(yaw), Math.sin(pitch), Math.cos(pitch) * Math.sin(-yaw));
	}

	/**
	 * Reverse a direction
	 *
	 * @param v
	 *            the direction
	 * @return the reversed direction
	 */
	public static Vector reverse(Vector v)
	{
		v.setX(-v.getX());
		v.setY(-v.getY());
		v.setZ(-v.getZ());
		return v;
	}

	/**
	 * Get a speed value from a vector (velocity)
	 *
	 * @param v
	 *            the vector
	 * @return the speed
	 */
	public static double getSpeed(Vector v)
	{
		Vector vi = new Vector(0, 0, 0);
		Vector vt = new Vector(0, 0, 0).add(v);

		return vi.distance(vt);
	}
}