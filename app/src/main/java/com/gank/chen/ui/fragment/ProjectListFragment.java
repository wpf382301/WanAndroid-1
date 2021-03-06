package com.gank.chen.ui.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.gank.chen.R;
import com.gank.chen.adapter.ProjectsListAdapter;
import com.gank.chen.base.BaseFragment;
import com.gank.chen.base.CreatePresenter;
import com.gank.chen.common.ConstantMap;
import com.gank.chen.common.RouterUrlManager;
import com.gank.chen.mvp.model.ArticleListModel;
import com.gank.chen.mvp.model.ArticleModel;
import com.gank.chen.mvp.presenter.ProjectsListPresenter;
import com.gank.chen.mvp.view.ImpProjectsListFragment;
import com.gank.chen.util.RouterUtil;
import com.gank.chen.util.SharePreferenceUtil;
import com.gank.chen.util.SnackbarUtils;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.List;

import butterknife.BindView;

/**
 * 项目列表页面
 *
 * @author chenbo
 */
@CreatePresenter(ProjectsListPresenter.class)
@Route(path = RouterUrlManager.PROJECTLIST_FRAGMENT)
public class ProjectListFragment extends BaseFragment<ImpProjectsListFragment, ProjectsListPresenter>
        implements ImpProjectsListFragment {

    @BindView(R.id.recyclerview_home_pic)
    RecyclerView recyclerviewHomePic;

    private ProjectsListAdapter adapter;
    private List<ArticleListModel> videoBeanList;
    private int id;


    @Override
    public int getViewLayoutId() {
        return R.layout.fragment_home_recommend;
    }

    @Override
    public void initData() {
        assert getArguments() != null;
        String type = "id";
        id = getArguments().getInt(type);
        getPresenter().getProjectsList(getActivity(), stateView, id, page);
    }

    @Override
    public void initView() {
        initPageNum(1);
        recyclerviewHomePic.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new ProjectsListAdapter(videoBeanList);
        recyclerviewHomePic.setAdapter(adapter);
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                ArticleListModel bean = (ArticleListModel) adapter.getData().get(position);
                Bundle bundle = new Bundle();
                bundle.putString("weburl", bean.getLink());
                bundle.putString("title", bean.getTitle());
                RouterUtil.goToActivity(RouterUrlManager.DETAIL, bundle);
            }
        });
        adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() {
            @Override
            public void onItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                if (RouterUtil.checkLoginState(recyclerviewHomePic)) {
                    ArticleListModel data = (ArticleListModel) adapter.getData().get(position);
                    if (data.isCollect()) {
                        getPresenter().onUnCollect(getActivity(), data.getId(), position);
                    } else {
                        getPresenter().onCollect(getActivity(), data.getId(), position);
                    }
                }
            }
        });
    }

    @Override
    public void onCollectSucess(int position) {
        adapter.getData().get(position).setCollect(true);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onUnCollectSucess(int position) {
        adapter.getData().get(position).setCollect(false);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadSucess(ArticleModel articleModel) {
        videoBeanList = articleModel.getDatas();
        super.onLoadSucess(videoBeanList);
        adapter.setNewData(videoBeanList);
    }

    @Override
    protected void onLoadMoreData(RefreshLayout refreshLayout) {
        super.onLoadMoreData(refreshLayout);
        getPresenter().getMoreProjectsList(getActivity(), id, page);
    }

    @Override
    public void onLoadMoreSuccess(ArticleModel articleModel) {
        super.onLoadMoreSuccess(articleModel.getDatas());
        adapter.addData(articleModel.getDatas());
    }
}
