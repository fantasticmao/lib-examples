import open3d as o3d


def test_quick_start():
    mesh = o3d.geometry.TriangleMesh.create_sphere()
    mesh.compute_vertex_normals()
    o3d.visualization.draw(mesh, raw_mode=True)
