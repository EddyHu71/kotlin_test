class MemberAdapter(
    private var list: List<Member>
) : RecyclerView.Adapter<MemberAdapter.VH>() {

    class VH(val view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_member, parent, false)
        return VH(view)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = list[position]

        holder.view.findViewById<TextView>(R.id.tvName).text = item.name
        holder.view.findViewById<TextView>(R.id.tvNik).text = item.nik

        val status = if (item.isSynced) "Synced" else "Draft"
        holder.view.findViewById<TextView>(R.id.tvStatus).text = status
    }

    override fun getItemCount() = list.size

    fun update(newList: List<Member>) {
        list = newList
        notifyDataSetChanged()
    }
}